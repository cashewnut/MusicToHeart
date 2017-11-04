package server.domain;

public class GetSongInfoResult {

    private boolean status;

    private String result;

    private SongInfo songInfo;

    public GetSongInfoResult() {
        //Default Constructor
    }

    public GetSongInfoResult(boolean status, String result, SongInfo songInfo) {
        this.status = status;
        this.result = result;
        this.songInfo = songInfo;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public SongInfo getSongInfo() {
        return songInfo;
    }

    public void setSongInfo(SongInfo songInfo) {
        this.songInfo = songInfo;
    }
}
