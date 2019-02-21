package com.marino.dsm.hello;


import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GameQuestion {
    private String question;
    private Map<String, Boolean> answerMap;
    private boolean done = false;

    public GameQuestion(){}

    public GameQuestion(String question, List<String> answers){
        this.question = question;
        this.answerMap = answers.stream().collect(Collectors.toMap(a -> a, a -> false, (u, v) -> {
                    throw new IllegalStateException(String.format("Duplicate key %s", u));
                },
                LinkedHashMap::new));
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public Map<String, Boolean> getAnswerMap() {
        return answerMap;
    }

    public void setAnswerMap(Map<String, Boolean> answerMap) {
        this.answerMap = answerMap;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }
}
