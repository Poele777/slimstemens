package com.marino.dsm.hello;

public class LastAnswer {
    private boolean lastAnswer;

    public LastAnswer(){};

    public LastAnswer(boolean lastAnswer) {
        this.lastAnswer = lastAnswer;
    }

    public boolean isLastAnswer() {
        return lastAnswer;
    }

    public void setLastAnswer(boolean lastAnswer) {
        this.lastAnswer = lastAnswer;
    }
}
