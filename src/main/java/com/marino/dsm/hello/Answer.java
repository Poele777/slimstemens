package com.marino.dsm.hello;

public class Answer {
    private boolean lastAnswer;
    private String answer;

    public Answer(){}

    public Answer(boolean lastAnswer, String answer) {
        this.lastAnswer = lastAnswer;
        this.answer = answer;
    }

    public boolean isLastAnswer() {
        return lastAnswer;
    }

    public void setLastAnswer(boolean lastAnswer) {
        this.lastAnswer = lastAnswer;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
