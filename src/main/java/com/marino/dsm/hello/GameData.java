package com.marino.dsm.hello;


import java.util.List;

public class GameData {

    private int timePlayerOne;
    private int timePlayerTwo;

    private List<GameQuestion> gameQuestions;

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

    public List<GameQuestion> getGameQuestions() {
        return gameQuestions;
    }

    public void setGameQuestions(List<GameQuestion> gameQuestions) {
        this.gameQuestions = gameQuestions;
    }
}
