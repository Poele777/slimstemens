package com.marino.dsm;

import com.marino.dsm.hello.GameQuestion;
import com.marino.dsm.hello.util.ReadQuestionsUtil;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;


public class QuestionParseTest {

    private ReadQuestionsUtil readQuestionsUtil = new ReadQuestionsUtil();

    @Test
    public void parseTest() {
        List<GameQuestion> questions = readQuestionsUtil.readQuestionsFile();
        Assert.assertEquals(2, questions.size());
        GameQuestion gameQuestion1 = questions.get(0);
        GameQuestion gameQuestion2 = questions.get(1);

        Assert.assertEquals("Vraag 1?",gameQuestion1.getQuestion());
        Assert.assertEquals("Vraag 2?",gameQuestion2.getQuestion());
    }

}
