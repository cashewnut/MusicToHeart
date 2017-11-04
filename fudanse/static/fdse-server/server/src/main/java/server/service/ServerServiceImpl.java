package server.service;

import org.springframework.stereotype.Service;
import server.domain.GetSongInfoResult;
import server.domain.PostSongInfo;
import server.domain.PostSongInfoResult;
import server.domain.SongInfo;
import java.util.ArrayList;
import java.util.UUID;

@Service
public class ServerServiceImpl implements ServerService{

    private ArrayList<SongInfo> songInfos = new ArrayList<>();

    @Override
    public PostSongInfoResult postSongList(PostSongInfo info) {
        SongInfo songInfo = new SongInfo();
        songInfo.setEmotionType(info.getEmotionType());
        songInfo.setSongNameList(info.getSongNameList());
        songInfo.setId(UUID.randomUUID().toString());

        songInfos.add(songInfo);

        PostSongInfoResult result = new PostSongInfoResult();
        result.setStatus(true);
        result.setMessage("Success");
        result.setInfo(songInfo);
        return result;
    }

    @Override
    public GetSongInfoResult getSongResult() {
        GetSongInfoResult result = new GetSongInfoResult();
        if(songInfos.isEmpty() == false){
            SongInfo songInfo = songInfos.get(0);
            songInfos.remove(0);
            result.setStatus(true);
            result.setResult("Success");
            result.setSongInfo(songInfo);
        }else{
            result.setStatus(false);
            result.setResult("Empry Song List");
            result.setSongInfo(null);
        }
        return result;
    }
}
