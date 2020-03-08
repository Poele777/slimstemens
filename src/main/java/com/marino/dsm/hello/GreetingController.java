package com.marino.dsm.hello;

import com.marino.dsm.hello.util.ReadQuestionsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;

@Controller
public class GreetingController {

    private GameData gameData;

    @Autowired
    public GreetingController(ReadQuestionsUtil readQuestionsUtil){
        gameData = new GameData();
        gameData.setGameQuestions(readQuestionsUtil.readQuestionsFile());
    }

    @MessageMapping("/load")
    @SendTo("/topic/loaded")
    public AnswerList load(LoadGame loadGame){
        gameData.setTimePlayerOne(loadGame.getTimePlayerOne());
        gameData.setTimePlayerTwo(loadGame.getTimePlayerTwo());
        return new AnswerList(gameData.getCurrentQuestion().getQuestion(), loadGame.getTimePlayerOne(), loadGame.getTimePlayerTwo(), loadGame.getNamePlayerOne(), loadGame.getNamePlayerTwo(), gameData.getCurrentQuestion().getAnswerMap());
    }
    @MessageMapping("/reload")
    @SendTo("/topic/loaded")
    public AnswerList reload(LoadGame loadGame){
        gameData.setTimePlayerOne(loadGame.getTimePlayerOne());
        gameData.setTimePlayerTwo(loadGame.getTimePlayerTwo());
        return new AnswerList(gameData.getCurrentQuestion().getQuestion(), gameData.getTimePlayerOne(), gameData.getTimePlayerTwo(), loadGame.getNamePlayerOne(), loadGame.getNamePlayerTwo(), gameData.getCurrentQuestion().getAnswerMap());
    }

    @MessageMapping("/next")
    @SendTo("/topic/next")
    public AnswerList next(){
        gameData.getCurrentQuestion().setDone(true);
        return new AnswerList(gameData.getCurrentQuestion().getQuestion(), gameData.getCurrentQuestion().getAnswerMap());
    }

    @MessageMapping("/answerGiven")
    @SendTo("/topic/answerGiven")
    public Answer answerGiven(String answer){
        gameData.getCurrentQuestion().getAnswerMap().put(answer, true);
        boolean lastAnswer = true;
        for(String key:gameData.getCurrentQuestion().getAnswerMap().keySet()){
            if(gameData.getCurrentQuestion().getAnswerMap().get(key) == false){
                lastAnswer = false;
                break;
            }
        }
        return new Answer(lastAnswer, answer);
    }

    @MessageMapping("/start")
    @SendTo("/topic/start")
    public Dummy start(){
        return new Dummy();
    }

    @MessageMapping("/stop")
    @SendTo("/topic/stop")
    public Dummy stop(){
        return new Dummy();
    }

    @MessageMapping("/continue")
    @SendTo("/topic/continue")
    public Dummy cont(){
        return new Dummy();
    }

}
