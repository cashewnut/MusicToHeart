package server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import server.domain.GetSongInfoResult;
import server.domain.PostSongInfo;
import server.domain.PostSongInfoResult;
import server.service.ServerService;

@RestController
public class ServerController {

    @Autowired
    private ServerService service;

    @CrossOrigin(origins = "*")
    @RequestMapping(value="/test",method= RequestMethod.GET)
    public String create(){
        return "Server Service";
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value="/postSongs",method= RequestMethod.POST)
    public PostSongInfoResult create(@RequestBody PostSongInfo info){
        System.out.println("[Server Service] Post. Emotion:" + info.getEmotionType() + " Size:" + info.getSongNameList().size());
        return service.postSongList(info);
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/getSongs", method = RequestMethod.GET)
    public GetSongInfoResult get(){
        System.out.println("[Server Service] Get.");
        return service.getSongResult();
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/showAll", method = RequestMethod.GET)
    public String showAll(){
        System.out.println("[Server Service] Show All.");
        return service.showAll();
    }

}
