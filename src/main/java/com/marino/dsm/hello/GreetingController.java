package com.marino.dsm.hello;

import com.marino.dsm.hello.util.ReadQuestionsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;

@Controller
public class GreetingController {

    private GameData gameData;
    private GameQuestion currentQuestion;

    @Autowired
    public GreetingController(ReadQuestionsUtil readQuestionsUtil){
        gameData = new GameData();
        gameData.setGameQuestions(readQuestionsUtil.readQuestionsFile());
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
        return new AnswerList(currentQuestion.getQuestion(), loadGame.getTimePlayerOne(), loadGame.getTimePlayerTwo(), loadGame.getNamePlayerOne(), loadGame.getNamePlayerTwo(), new ArrayList<>(currentQuestion.getAnswerMap().keySet()));
    }

    @MessageMapping("/next")
    @SendTo("/topic/next")
    public AnswerList next(){
        currentQuestion.setDone(true);
        currentQuestion = findFirstNotDoneQuestion(gameData.getGameQuestions());
        return new AnswerList(currentQuestion.getQuestion(), new ArrayList<>(currentQuestion.getAnswerMap().keySet()));
    }

    @MessageMapping("/answerGiven")
    @SendTo("/topic/answerGiven")
    public Answer answerGiven(String answer){
        currentQuestion.getAnswerMap().put(answer, true);
        return new Answer(answer);
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

    private GameQuestion findFirstNotDoneQuestion(Map<String, GameQuestion> gameQuestions) {
        Optional<Map.Entry<String, GameQuestion>> first = gameQuestions.entrySet().stream().filter(g -> !g.getValue().isDone()).findFirst();
        return first.get().getValue();
    }
}
