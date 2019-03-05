package com.marino.dsm.hello;

import java.util.ArrayList;
import java.util.List;

public class AnswerList {
    private int timePlayerOne;
    private int timePlayerTwo;
    private String namePlayerOne;
    private String namePlayerTwo;
    private String answer1;
    private String answer2;
    private String answer3;
    private String answer4;
    private String answer5;
    private String question;

    public AnswerList(String question, int timePlayerOne, int timePlayerTwo,String namePlayerOne, String namePlayerTwo, ArrayList<String> answers) {
        this(question, answers);
        this.timePlayerOne = timePlayerOne;
        this.timePlayerTwo = timePlayerTwo;
        this.namePlayerOne = namePlayerOne;
        this.namePlayerTwo = namePlayerTwo;
    }

    public AnswerList(String question, List<String> answers){
        this.answer1 = answers.get(0);
        this.answer2 = answers.get(1);
        this.answer3 = answers.get(2);
        this.answer4 = answers.get(3);
        this.answer5 = answers.get(4);
        this.question = question;
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

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getNamePlayerOne() {
        return namePlayerOne;
    }

    public void setNamePlayerOne(String namePlayerOne) {
        this.namePlayerOne = namePlayerOne;
    }

    public String getNamePlayerTwo() {
        return namePlayerTwo;
    }

    public void setNamePlayerTwo(String namePlayerTwo) {
        this.namePlayerTwo = namePlayerTwo;
    }
}
