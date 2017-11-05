package server.service;

import server.domain.GetSongInfoResult;
import server.domain.PostSongInfo;
import server.domain.PostSongInfoResult;

public interface ServerService {

    PostSongInfoResult postSongList(PostSongInfo info);

    GetSongInfoResult getSongResult();

    String showAll();

    String clearAll();

}
