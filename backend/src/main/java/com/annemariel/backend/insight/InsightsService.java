package com.annemariel.backend.insight;

import com.annemariel.backend.expense.ExpenseService;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class InsightsService {
    private final ExpenseService expenseService;

    public InsightsService(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    public String getInsights() {
        String prompt = "Given the following data representing my expenses, please analyze the data and provide concise personalized insights into the summary of my spending habits. Highlight the sum and the percentage per each category based on total expenses amount(unordered list). Add top 3 practical and street-smart recommendations based on my expenses(ordered list). The response should be in html markup that I can insert into a div innerHTML. Use h2 tags for the title of summary and recommendations. Currency is in SEK.";

        var expenses = expenseService.findAllExpenses().stream().map(Object::toString).collect(Collectors.joining(", "));

        // Ideally, this information should be stored in application properties or environment variables, not hard-coded
        String apiKey = "";
        String sessionID = "";

        // Prepare the request body
        Map<String, Object> body = new HashMap<>();
        body.put("apiKey", apiKey);
        body.put("sessionID", sessionID);
        List<Map<String, String>> history = new ArrayList<>();
        Map<String, String> userHistory = new HashMap<>();
        userHistory.put("role", "user");
        userHistory.put("content", prompt + " " + expenses);
        history.add(userHistory);
        body.put("history", history);

        // Create headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // Create a new HttpEntity
        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(body, headers);

        // Create a new RestTemplate instance
        RestTemplate restTemplate = new RestTemplate();

        // Send the POST request
        ResponseEntity<String> response = restTemplate.exchange(
                "https://api.afforai.com/api/api_completion",
                HttpMethod.POST,
                entity,
                String.class
        );

        return response.getBody();
    }
}
