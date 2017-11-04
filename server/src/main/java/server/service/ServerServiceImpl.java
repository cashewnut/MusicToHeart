package server.service;

import com.google.gson.Gson;
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
            songInfos.add(songInfo);
            result.setStatus(true);
            result.setResult("Success");
            result.setSongInfo(songInfo);
        }else{
            result.setStatus(false);
            result.setResult("Empty Song List");
            result.setSongInfo(null);
            System.out.println("[Server Service]已经没有数据可以拿了");
        }
        return result;
    }

    @Override
    public String showAll() {
        if(songInfos.isEmpty()){
            return "当前没有任何数据待存储";
        }else{
            String result = "";
            Gson gson = new Gson();
            for(int i = 0;i < songInfos.size();i++){
                String temp = gson.toJson(songInfos.get(i)) + "\n";
                result += temp;
            }
            return result;
        }
    }
}
