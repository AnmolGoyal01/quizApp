package com.example.quiz.controller;

import com.example.quiz.model.QuestionModel;
import com.example.quiz.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/questions")
public class QuestionsController {

    @Autowired
    private QuestionService questionService;

    @GetMapping
    public ResponseEntity<List<QuestionModel>> getAllQuestions() {
        return questionService.getAllQuestions();
    }
    @GetMapping("id/{questionId}")
    public ResponseEntity<QuestionModel> getQuestionById(@PathVariable Integer questionId) {
        return questionService.getQuestionById(questionId);
    }
    @GetMapping("level/{difficulty}")
    public ResponseEntity<List<QuestionModel>> getQuestionsByDifficultyLevel(@PathVariable String difficulty) {
        return questionService.getQuestionsByDifficultyLevel(difficulty);
    }
    @GetMapping("category/{cat}")
    public ResponseEntity<List<QuestionModel>> getQuestionsByCategory(@PathVariable String cat) {
        return questionService.getQuestionsByCategory(cat);
    }

    @PostMapping
    public ResponseEntity<QuestionModel> addQuestion(@RequestBody QuestionModel question) {
        return questionService.addQuestion(question);
    }

    @PutMapping("id/{questionId}")
    public ResponseEntity<QuestionModel> editQuestion(@PathVariable Integer questionId, @RequestBody QuestionModel question) {
        return questionService.editQuestion(questionId, question);
    }

    @DeleteMapping("id/{questionId}")
    public ResponseEntity<?> deleteQuestion(@PathVariable Integer questionId) {
        return questionService.deleteQuestion(questionId);
    }
}
