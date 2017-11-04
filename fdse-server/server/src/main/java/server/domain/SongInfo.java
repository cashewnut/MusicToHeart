package server.domain;

import java.util.ArrayList;

public class SongInfo {

    private String id;

    private int emotionType;

    private ArrayList<String> songNameList;

    public SongInfo() {
        //Default Constructor
    }

    public SongInfo(String id, int emotionType, ArrayList<String> songNameList) {
        this.id = id;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
