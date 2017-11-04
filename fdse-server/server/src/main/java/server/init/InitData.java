package server.init;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import server.domain.PostSongInfo;
import server.service.ServerService;

import java.util.ArrayList;

@Component
public class InitData implements CommandLineRunner {

    @Autowired
    ServerService service;

    @Override
    public void run(String... args) throws Exception {

        PostSongInfo info = new PostSongInfo();

        ArrayList<String> musicList = new ArrayList<>();
        musicList.add("1.mp3");
        musicList.add("2.mp3");
        musicList.add("3.mp3");

        info.setEmotionType(0);
        info.setSongNameList(musicList);
        service.postSongList(info);

        musicList = new ArrayList<>();
        musicList.add("4.mp3");
        musicList.add("5.mp3");
        musicList.add("6.mp3");

        info.setEmotionType(1);
        info.setSongNameList(musicList);
        service.postSongList(info);

        musicList = new ArrayList<>();
        musicList.add("7.mp3");
        musicList.add("8.mp3");
        musicList.add("9.mp3");

        info.setEmotionType(2);
        info.setSongNameList(musicList);
        service.postSongList(info);

        musicList = new ArrayList<>();
        musicList.add("10.mp3");
        musicList.add("11.mp3");
        musicList.add("12.mp3");

        info.setEmotionType(3);
        info.setSongNameList(musicList);
        service.postSongList(info);

        musicList = new ArrayList<>();
        musicList.add("13.mp3");
        musicList.add("14.mp3");
        musicList.add("15.mp3");

        info.setEmotionType(4);
        info.setSongNameList(musicList);
        service.postSongList(info);

        musicList = new ArrayList<>();
        musicList.add("16.mp3");

        info.setEmotionType(5);
        info.setSongNameList(musicList);
        service.postSongList(info);

        musicList = new ArrayList<>();//Empty Set

        info.setEmotionType(6);
        info.setSongNameList(musicList);
        service.postSongList(info);

    }

}
