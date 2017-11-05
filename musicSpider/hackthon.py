# -*- coding: utf-8 -*-

from NetEaseMusicApi import api, save_song, save_album
import json
import os
import re
import requests
import re
import requests
import json
import urllib.request
import os
from bs4 import BeautifulSoup

minimumsize = 1
album_list = []
final_data = {}
mm = []

def get_Song():
    print("asd")

def parsehtmlMusicList(html):

    soup = BeautifulSoup(html, 'lxml')
    list_pic = soup.select('ul#m-pl-container li div img')
    list_nameUrl = soup.select('ul#m-pl-container li div a.msk')
    #list_num = soup.select('div.bottom span.nb')
    list_author = soup.select('ul#m-pl-container li p a')
    n = 0
    length = len(list_pic)
    print(length)
    while n < length:
        #print(list_pic[n]['src']+'\n\n')
        album_list.append("http://music.163.com" + list_nameUrl[n]['href'])
        print(list_nameUrl[n]['href']+'\n\n')
        #print(+list_num[n].text+'\n\n')
        #print(list_author[n]['title']+'\n\n')
        n += 1


headers = {     'Referer':'http://music.163.com/',
                'Host':'music.163.com',
                'User-Agent': 'Mozilla/5.0 (X11; Linux x86 64; rv:38.0) '
                              'Gecko/20100101 Firefox/38.0 Iceweasel/38.3.0',
                'Accept':'text/html,application/xhtml xml,application/xml;q=0.9,*/*;q=0.8'    }

s = requests.session()
url = 'http://music.163.com/discover/playlist/?cat=%E6%AC%A7%E7%BE%8E'
url = s.get(url,headers = headers).content

#url2 = "http://music.163.com/playlist?id=911557338"
#url2 = s.get(url2,headers = headers).content

parsehtmlMusicList(url)

#for x_it in range(0,len(album_list)):
for x_it in range(7,9):
    url2 = album_list[x_it]
    print(url2)

    s = requests.session()

    try:
        url2 = s.get(url2, headers=headers).content

    except:
        print("url2 fault")
        continue
        pass

    s = BeautifulSoup(url2,"lxml")
    main = s.find('ul',{'class':'f-hide'})
    songs_url = []

    print("asdasdasdasdasd",main)


    for music in main.find_all('a'):

        song_name = re.findall(".*>(.*)<.*",str(music))
        mm.append(song_name[0])


    for music in main.find_all('a'):

        song_name = re.findall(".*>(.*)<.*",str(music))
        print(song_name[0]) #song name

        re_all= re.findall(r'\d+','{}'.format(music['href']))
        song_num = int(re_all[0])

        print(re_all[0])   #ID

        lrc_url = 'http://music.163.com/api/song/lyric?' + 'id=' + re_all[0] + '&lv=1&kv=1&tv=-1'
        lyric = requests.get(lrc_url)
        json_obj = lyric.text
        j = json.loads(json_obj)
        try:
            lrc = j['lrc']['lyric']
            pass
        except:
            print(re_all[0]+" has no lrcs")
            continue
            pass
        pat = re.compile(r'\[.*\]')
        lrc = re.sub(pat, "", lrc)
        lrc = lrc.strip()

        try:
            with open(song_name[0] + ".txt", 'w',encoding="UTF-8") as file_object:
                file_object.write(lrc)
                file_object.close()
            pass
        except:
            continue
            pass

        songs_url.append(song_num)
        print("error1111111 ",songs_url)


    for x in range(0,2):
        for value in mm:
            url = 'http://sug.music.baidu.com/info/suggestion'
            payload = {'word': value, 'version': '2', 'from': '0'}
            print("Song Name: " + value)

            r = requests.get(url, params=payload)
            contents = r.text
            d = json.loads(contents, encoding="utf-8")
            if ('data' not in d):
                continue
            if ('song' not in d["data"]):
                continue
            songid = d["data"]["song"][0]["songid"]
            print("Song ID: " + songid)

            url = "http://music.baidu.com/data/music/fmlink"
            payload = {'songIds': songid, 'type': 'mp3'}
            r = requests.get(url, params=payload)
            contents = r.text
            d = json.loads(contents, encoding="utf-8")
            if d is not None and 'data' not in d or d['data'] == '':
                continue
            songlink = d["data"]["songList"][0]["songLink"]
            if (len(songlink) < 10):
                print("do not have flac\n")
                continue

            songdir = "songs"
            if not os.path.exists(songdir):/
                os.makedirs(songdir)

            songname = d["data"]["songList"][0]["songName"]
            artistName = d["data"]["songList"][0]["artistName"]
            filename = "./" + songdir + "/" + songname + "-" + artistName + ".flac"

            print(filename+"asdasdasdasdasdasdasdas")

            f = urllib.request.urlopen(songlink)
            headers = requests.head(songlink).headers
            size = int(headers['Content-Length']) / (1024 ** 2)

            if not os.path.isfile(filename) or os.path.getsize(filename) < minimumsize:
                print("%s is downloading now ......\n" % songname)
                try:
                    with open(filename, "wb") as code:
                        code.write(f.read())
                except:
                    print("song")
            else:
                print("%s is already downloaded. Finding next song...\n\n" % songname)

    music = 0
    x = 0
    x_it= 0
    value = 0

    print("Set finish ***********************")