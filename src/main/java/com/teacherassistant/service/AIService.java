package com.teacherassistant.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class AIService {

    private final WebClient webClient = WebClient.create("https://api.openai.com/v1");

    @Value("${ai.api.key}")
    private String API_KEY;

    public String generateQuestions(String classLevel, String subject, String topic, String difficulty, int count) {

        String prompt = "Generate " + count + " " +difficulty+ " level questions with answers for class " + classLevel +
                ", subject " + subject +
                ", topic " + topic +
                " in JSON format like: {questions:[{question:'',answer:''}]}";

        String requestBody = """
        {
          "model": "gpt-4o-mini",
          "messages": [
            {
              "role": "user",
              "content": "%s"
            }
          ]
        }
        """.formatted(prompt);

        try {
            String response = webClient.post()
                    .uri("/chat/completions")
                    .header("Authorization", "Bearer " + API_KEY)
                    .header("Content-Type", "application/json")
                    .bodyValue(requestBody)
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();

            ObjectMapper mapper = new ObjectMapper();

            JsonNode root = mapper.readTree(response);

            String content = root
                    .path("choices")
                    .get(0)
                    .path("message")
                    .path("content")
                    .asText();

            content = content.replace("```json", "")
                    .replace("```", "")
                    .trim();

            return content;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error occurred while creating questions");
        }
    }
}