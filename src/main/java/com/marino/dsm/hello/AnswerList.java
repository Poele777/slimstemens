package com.marino.dsm.hello;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

    private boolean answered1;
    private boolean answered2;
    private boolean answered3;
    private boolean answered4;
    private boolean answered5;

    private String question;

    public AnswerList(String question, int timePlayerOne, int timePlayerTwo, String namePlayerOne, String namePlayerTwo, Map<String, Boolean> answers) {
        this(question, answers);
        this.timePlayerOne = timePlayerOne;
        this.timePlayerTwo = timePlayerTwo;
        this.namePlayerOne = namePlayerOne;
        this.namePlayerTwo = namePlayerTwo;
    }

    public AnswerList(String question, Map<String, Boolean> answers){
        List<String> answerList = new ArrayList<>(answers.keySet());
        this.answer1 = answerList.get(0);
        this.answer2 = answerList.get(1);
        this.answer3 = answerList.get(2);
        this.answer4 = answerList.get(3);
        this.answer5 = answerList.get(4);
        this.answered1 = answers.get(answer1);
        this.answered2 = answers.get(answer2);
        this.answered3 = answers.get(answer3);
        this.answered4 = answers.get(answer4);
        this.answered5 = answers.get(answer5);

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

    public boolean isAnswered1() {
        return answered1;
    }

    public void setAnswered1(boolean answered1) {
        this.answered1 = answered1;
    }

    public boolean isAnswered2() {
        return answered2;
    }

    public void setAnswered2(boolean answered2) {
        this.answered2 = answered2;
    }

    public boolean isAnswered3() {
        return answered3;
    }

    public void setAnswered3(boolean answered3) {
        this.answered3 = answered3;
    }

    public boolean isAnswered4() {
        return answered4;
    }

    public void setAnswered4(boolean answered4) {
        this.answered4 = answered4;
    }

    public boolean isAnswered5() {
        return answered5;
    }

    public void setAnswered5(boolean answered5) {
        this.answered5 = answered5;
    }
}
