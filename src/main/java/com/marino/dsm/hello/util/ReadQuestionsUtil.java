package com.marino.dsm.hello.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.marino.dsm.hello.GameQuestion;
import com.marino.dsm.hello.Question;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class ReadQuestionsUtil {

    public Map<String,GameQuestion> readQuestionsFile() {
        try {
            ClassLoader classLoader = getClass().getClassLoader();
            File file = new File(classLoader.getResource("questions/questions.json").getFile());
            String json = new String(Files.readAllBytes(Paths.get(file.getAbsolutePath())));
            ObjectMapper objectMapper = new ObjectMapper();
            List<Question> questions = objectMapper.readValue(json, new TypeReference<List<Question>>() {
            });
            return convertToGameQuestion(questions);
        } catch (IOException ex) {
            ex.printStackTrace();
            throw new RuntimeException("Something went wrong parsing the questions");
        }
    }

    public Map<String, GameQuestion> convertToGameQuestion(List<Question> questions) {
        LinkedHashMap<String, GameQuestion> blaat = questions.stream().collect(Collectors.toMap(a -> a.getQuestion(), a -> new GameQuestion(a.getQuestion(), a.getAnswers()), (u, v) -> {
                    throw new IllegalStateException(String.format("Duplicate key %s", u));
                },
                LinkedHashMap::new));
        return blaat;
    }
}
