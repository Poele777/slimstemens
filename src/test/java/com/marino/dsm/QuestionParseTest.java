package com.marino.dsm;

import com.marino.dsm.hello.util.ReadQuestionsUtil;
import org.junit.Assert;
import org.junit.Test;


public class QuestionParseTest {

    private ReadQuestionsUtil readQuestionsUtil = new ReadQuestionsUtil();

    @Test
    public void parseTest() {
        Assert.assertEquals(2, readQuestionsUtil.readQuestionsFile().size());
    }

}
