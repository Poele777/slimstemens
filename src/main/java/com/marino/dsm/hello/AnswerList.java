package com.marino.dsm.hello;

import java.util.ArrayList;
import java.util.List;

public class AnswerList {
    private int timePlayerOne;
    private int timePlayerTwo;
    private String answer1;
    private String answer2;
    private String answer3;
    private String answer4;
    private String answer5;

    public AnswerList(int timePlayerOne, int timePlayerTwo, ArrayList<String> answers) {
        this(answers);
        this.timePlayerOne = timePlayerOne;
        this.timePlayerTwo = timePlayerTwo;
    }

    public AnswerList(List<String> answers){
        answer1 = answers.get(0);
        answer2 = answers.get(1);
        answer3 = answers.get(2);
        answer4 = answers.get(3);
        answer5 = answers.get(4);
    }

    public String getAnswer1() {
        return answer1;
    }

    public void setAnswer1(String answer1) {
        this.answer1 = answer1;
    }

    public String getAnswer2() {
        return answer2;
    }

    public void setAnswer2(String answer2) {
        this.answer2 = answer2;
    }

    public String getAnswer3() {
        return answer3;
    }

    public void setAnswer3(String answer3) {
        this.answer3 = answer3;
    }

    public String getAnswer4() {
        return answer4;
    }

    public void setAnswer4(String answer4) {
        this.answer4 = answer4;
    }

    public String getAnswer5() {
        return answer5;
    }

    public void setAnswer5(String answer5) {
        this.answer5 = answer5;
    }

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
}
