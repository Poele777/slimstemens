package com.marino.dsm.hello.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.marino.dsm.hello.Question;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@Component
public class ReadQuestionsUtil {

    public List<Question> readQuestionsFile() {
        try {
            ClassLoader classLoader = getClass().getClassLoader();
            File file = new File(classLoader.getResource("questions/questions.json").getFile());
            String json = new String(Files.readAllBytes(Paths.get(file.getAbsolutePath())));
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(json, new TypeReference<List<Question>>() {
            });
        } catch (IOException ex) {
            ex.printStackTrace();
            throw new RuntimeException("Something went wrong parsing the questions");
        }
    }
}
