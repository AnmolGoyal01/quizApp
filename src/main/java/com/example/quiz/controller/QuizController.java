package com.example.quiz.controller;

import com.example.quiz.model.QuizModel;
import com.example.quiz.model.Response;
import com.example.quiz.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("quiz")
public class QuizController {
    @Autowired
    private QuizService quizService;

    @PostMapping
    public ResponseEntity<?> createQuiz(@RequestParam String category, @RequestParam Integer numberOfQuestions, @RequestParam String title) {
        return quizService.createQuiz(category, numberOfQuestions, title);
    }
    @GetMapping("id/{quizId}")
    public ResponseEntity<?> getQuizById(@PathVariable Integer quizId) {
        return quizService.getQuizById(quizId);
    }
    @PostMapping("id/{quizId}")
    public ResponseEntity<?> submitQuiz(@PathVariable Integer quizId, @RequestParam List<Response> responses) {
        return quizService.submitQuiz(quizId, responses);
    }
}
