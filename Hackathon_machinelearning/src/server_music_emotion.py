import cv2
import os
import zerorpc
import numpy as np
import matplotlib.pyplot as plt
from subprocess import Popen,PIPE
import json
import pickle
from json import JSONDecoder

import urllib.request
import time
import requests

# 读歌词文本内容
def read_lrc(lrc_path):
    with open(lrc_path,encoding='gbk',mode='r',errors='ignore') as f:
        content = f.read()
        content = content.replace('\n','')
        #print(content)
    return content

# 歌词情感识别
from watson_developer_cloud import ToneAnalyzerV3
tone_analyzer = ToneAnalyzerV3(
   username='bf9d6cb6-61a8-462e-ba12-cd4c34916cec',
   password='jFxuvjVbNFk6',
   version='2016-05-19')

def tone_analyze(tone_analyzer,lrc_path):
#tone = tone_analyzer.tone('A word is dead when it is said, some say. Emily Dickinson')
#tone2 = tone_analyzer.tone('Monitor customer service and support conversations so you can respond to your customers appropriately and at scale.')
#info = json.dumps(tone, indent=2)
    text = read_lrc(lrc_path)
    if text == '':
        return None
    tone = tone_analyzer.tone(text)
    prob_combine = tone['document_tone']['tone_categories'][0]['tones'] + tone['document_tone']['tone_categories'][1]['tones']
    dicts = {}
    for dict_element in prob_combine:
        if dict_element['tone_name'] == 'Anger':
            dicts['angry'] = dict_element['score'] 
        if dict_element['tone_name'] == 'Fear':
            dicts['fear'] = dict_element['score'] 
        if dict_element['tone_name'] == 'Sadness':
            dicts['sad'] = dict_element['score'] 
        if dict_element['tone_name'] == 'Analytical':
            dicts['disgust'] = dict_element['score']
        if dict_element['tone_name'] == 'Confident':
            dicts['surprise'] = dict_element['score']
        if dict_element['tone_name'] == 'Joy':
            dicts['happy'] = dict_element['score'] 
        if dict_element['tone_name'] == 'Tentative':
            dicts['neutral'] = dict_element['score']
                  
    return dicts


def train_tone_analyze_for_batch(batch_path):
    Songs_dict = {}
    # 判断是否存在
    if os.path.exists('songs_emotion.pkl'):
        with open('songs_emotion.pkl','rb') as f:
            Songs_dict = pickle.load(f)
        return Songs_dict
    with open('songs_name.pkl','rb') as f:
        songs_name = pickle.load(f)
    filelist = os.listdir(batch_path)
    filelist = [file for file in filelist if file.endswith('txt') ]
    print(len(filelist))
    for file in filelist:
        
        filename = os.path.join(batch_path,file)
        prediction = tone_analyze(tone_analyzer,filename)
        if prediction == None:
            continue
        maxrank = sorted(prediction.items(),key=lambda d:d[1], reverse=True)[0]
        # dict[filename] = (emotion,value)
        file2 = songs_name[file[:-4]][0]
        print(file2)
        Songs_dict[file2] = (maxrank[0],maxrank[1])
    return Songs_dict

class SongsDict():
    
    def __init__(self,batch_path):
        self.songs_emotion = train_tone_analyze_for_batch(batch_path)
        self.emotion_songs = self.create_emotion_songs(self.songs_emotion)
        with open('songs_emotion.pkl','wb') as f:
            pickle.dump(self.songs_emotion,f)
        with open('emotion_songs.pkl','wb') as f:
            pickle.dump(self.emotion_songs,f)
        #print(self.emotion_songs)
        print('Now serving...')
        
    def create_emotion_songs(self,songs_emotion):
        emotion_song_dict = {}
        emotion_labels = ['Joy','Analytical','Sadness','Anger','Fear','Confident','Tentative']
        for item in songs_emotion.items():
            emotion = item[1][0]
            emotion_song_dict.setdefault(emotion,[]) 
            
            #print(emotion_song_dict)
            emotion_song_dict[emotion].append(item[0]+'.mp3')
        return emotion_song_dict
    
    def recommand(self,emotion):
        # It's a songs list
        ans = ''
        songs_list = self.emotion_songs[emotion]
        #number = len(songs_list)
        #index = np.random.randint(number)
        np.random.shuffle(songs_list)
        ans += ('emotion:'+emotion+' songs:'+songs_list[0])
        res = self.phone_login(emotion,songs_list)
        return ans+res
    
    # 调用结果！！
    def phone_login(self,emotion,songs_list): 
        http_url = 'http://10.141.212.24:14567/postSongs' 
        emotion_labels = ['angry','disgust','fear','happy','sad','surprise','neutral']
        method = "POST"
        emotion_index = emotion_labels.index(emotion)
        headers = {"Content-Type" : "application/json"}
        data_json = json.dumps({"emotionType":emotion_index,"songNameList":songs_list}).encode("utf-8")
        print(data_json)
        request = urllib.request.Request(http_url, data = data_json, method=method, headers=headers)
        with urllib.request.urlopen(request) as response:
            response_body = response.read().decode('utf-8')
            return response_body
    
    
s = zerorpc.Server(SongsDict('words_1'))
s.bind("tcp://0.0.0.0:4242")
s.run()
    
