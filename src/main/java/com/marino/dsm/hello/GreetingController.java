package com.marino.dsm.hello;

import com.marino.dsm.hello.util.ReadQuestionsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

import java.io.IOException;

@Controller
public class GreetingController {

    private GameData gameData;

    @Autowired
    public GreetingController(ReadQuestionsUtil readQuestionsUtil){
        gameData = new GameData();
        gameData.setGameQuestions(readQuestionsUtil.readQuestionsFile());
    }

    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public Greeting greeting(HelloMessage message) throws Exception {
        Thread.sleep(500); // simulated delay
        return new Greeting("Hello, " + HtmlUtils.htmlEscape(message.getName()) + "!");
    }

    @MessageMapping("/answer")
    @SendTo("/topic/answer")
    public Answer answer(Answer answer) throws Exception {
        return new Answer(answer.getAnswer());
    }

    @MessageMapping("/load")
    @SendTo("/topic/loaded")
    public String load(LoadGame loadGame){
        return "blaat";
    }
}
