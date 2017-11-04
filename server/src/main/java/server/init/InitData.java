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
        musicList.add("Forever.mp3");
        musicList.add("Garden.mp3");
        musicList.add("Freedom.mp3");

        info.setEmotionType(0);
        info.setSongNameList(musicList);
        service.postSongList(info);

        musicList = new ArrayList<>();
        musicList.add("First Love.mp3");
        musicList.add("Feels Like Home.mp3");
        musicList.add("Faking It.mp3");

        info.setEmotionType(1);
        info.setSongNameList(musicList);
        service.postSongList(info);

        musicList = new ArrayList<>();
        musicList.add("1000x.mp3");
        musicList.add("Runaway.mp3");
        musicList.add("Overcome.mp3");

        info.setEmotionType(2);
        info.setSongNameList(musicList);
        service.postSongList(info);

        musicList = new ArrayList<>();
        musicList.add("Reach.mp3");
        musicList.add("Asphyxia.mp3");
        musicList.add("Free Loop.mp3");

        info.setEmotionType(3);
        info.setSongNameList(musicList);
        service.postSongList(info);

        musicList = new ArrayList<>();
        musicList.add("Rather Be.mp3");
        musicList.add("I Do.mp3");
        musicList.add("Angelina.mp3");

        info.setEmotionType(4);
        info.setSongNameList(musicList);
        service.postSongList(info);

        musicList = new ArrayList<>();
        musicList.add("Tassel.mp3");

        info.setEmotionType(5);
        info.setSongNameList(musicList);
        service.postSongList(info);

        musicList = new ArrayList<>();//Empty Set

        info.setEmotionType(6);
        info.setSongNameList(musicList);
        service.postSongList(info);

    }

}
