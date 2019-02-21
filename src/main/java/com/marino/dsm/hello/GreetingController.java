package com.marino.dsm.hello;

import com.marino.dsm.hello.util.ReadQuestionsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;

@Controller
public class GreetingController {

    private GameData gameData;
    private GameQuestion currentQuestion;
    private boolean player1Active = false;

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
    public AnswerList load(LoadGame loadGame){
        gameData.setTimePlayerOne(loadGame.getTimePlayerOne());
        gameData.setTimePlayerTwo(loadGame.getTimePlayerTwo());
        currentQuestion = findFirstNotDoneQuestion(gameData.getGameQuestions());
        return new AnswerList(loadGame.getTimePlayerOne(), loadGame.getTimePlayerTwo(), new ArrayList<>(currentQuestion.getAnswerMap().keySet()));
    }

    @MessageMapping("/next")
    @SendTo("/topic/loaded")
    public AnswerList next(){
        //at same seconds the next round goes to the player who last answered
        if(gameData.getTimePlayerOne() < gameData.getTimePlayerTwo()){
            player1Active = true;
        }else if(gameData.getTimePlayerOne() > gameData.getTimePlayerTwo()){
            player1Active = false;
        }
        currentQuestion.setDone(true);
        currentQuestion = findFirstNotDoneQuestion(gameData.getGameQuestions());
        return new AnswerList(new ArrayList<>(currentQuestion.getAnswerMap().keySet()));
    }

    @MessageMapping("/answerGiven")
    @SendTo("/topic/answerGiven")
    public Answer answerGiven(String answer){
        currentQuestion.getAnswerMap().put(answer, true);
        return new Answer(answer);
    }

    @MessageMapping("/start")
    @SendTo("/topic/start")
    public void start(){
        //switchActivePlayer();
    }

    private void switchActivePlayer() {
        player1Active = !player1Active;
    }

    @MessageMapping("/stop")
    @SendTo("/topic/stop")
    public void stop(){
        switchActivePlayer();
    }

    private GameQuestion findFirstNotDoneQuestion(Map<String, GameQuestion> gameQuestions) {
        Optional<Map.Entry<String, GameQuestion>> first = gameQuestions.entrySet().stream().filter(g -> !g.getValue().isDone()).findFirst();
        return first.get().getValue();
    }
}
