package server.domain;

import java.util.ArrayList;

public class PostSongInfo {

    private int emotionType;

    private ArrayList<String> songNameList;

    public PostSongInfo() {
        //Default Constructor
    }

    public PostSongInfo(int emotionType, ArrayList<String> songNameList) {
        this.emotionType = emotionType;
        this.songNameList = songNameList;
    }

    public int getEmotionType() {
        return emotionType;
    }

    public void setEmotionType(int emotionType) {
        this.emotionType = emotionType;
    }

    public ArrayList<String> getSongNameList() {
        return songNameList;
    }

    public void setSongNameList(ArrayList<String> songNameList) {
        this.songNameList = songNameList;
    }
}
