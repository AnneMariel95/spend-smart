package com.annemariel.backend.insight;


import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin
@RequestMapping("/api/insights")
public class InsightsController {
    private final InsightsService insightsService;

    public InsightsController(InsightsService insightsService) {
        this.insightsService = insightsService;
    }

    @GetMapping
    public ResponseEntity<String> analyzeExpenses() {
        return ResponseEntity.ok(insightsService.getInsights());
    }
}
