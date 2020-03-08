package com.marino.dsm.hello;


import java.util.Map;
import java.util.Optional;

public class GameData {

    private int timePlayerOne;
    private int timePlayerTwo;

    private Map<String, GameQuestion> gameQuestions;

    public int getTimePlayerOne() {
        return timePlayerOne;
    }

    public void setTimePlayerOne(int timePlayerOne) {
        this.timePlayerOne = timePlayerOne;
    }

    public int getTimePlayerTwo() {
        return timePlayerTwo;
    }

    public void setTimePlayerTwo(int timePlayerTwo) {
        this.timePlayerTwo = timePlayerTwo;
    }

    public Map<String, GameQuestion> getGameQuestions() {
        return gameQuestions;
    }

    public void setGameQuestions(Map<String, GameQuestion> gameQuestions) {
        this.gameQuestions = gameQuestions;
    }

    public GameQuestion getCurrentQuestion(){
        return findFirstNotDoneQuestion();
    }
    private GameQuestion findFirstNotDoneQuestion() {
        Optional<Map.Entry<String, GameQuestion>> first = gameQuestions.entrySet().stream().filter(g -> !g.getValue().isDone()).findFirst();
        return first.get().getValue();
    }
}
