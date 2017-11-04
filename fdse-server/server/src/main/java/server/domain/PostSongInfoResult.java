package server.domain;

public class PostSongInfoResult {

    private boolean status;

    private String message;

    private SongInfo info;

    public PostSongInfoResult() {
        //Default Constructor
    }

    public PostSongInfoResult(boolean status, String message, SongInfo info) {
        this.status = status;
        this.message = message;
        this.info = info;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public SongInfo getInfo() {
        return info;
    }

    public void setInfo(SongInfo info) {
        this.info = info;
    }
}
