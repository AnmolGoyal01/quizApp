package com.example.quiz.service;

import com.example.quiz.model.QuestionModel;
import com.example.quiz.repository.QuestionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {
    @Autowired
    private QuestionDao questionDao;

    public ResponseEntity<List<QuestionModel>> getAllQuestions() {
        try {
            List<QuestionModel> allQuestions = questionDao.findAll();
            if (allQuestions.isEmpty()) {
                return new ResponseEntity<>(allQuestions, HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(allQuestions, HttpStatus.FOUND);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<QuestionModel> getQuestionById(Integer questionId) {
        try {
            Optional<QuestionModel> questionById = questionDao.findById(questionId);
            if(questionById.isEmpty()) {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(questionById.get(), HttpStatus.FOUND);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<List<QuestionModel>> getQuestionsByDifficultyLevel(String level) {
        try {
            List<QuestionModel> questionModelByDifficultyLevel = questionDao.findQuestionModelByDifficultyLevel(level);
            if(questionModelByDifficultyLevel.isEmpty()) {
                return new ResponseEntity<>(questionModelByDifficultyLevel,HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(questionModelByDifficultyLevel,HttpStatus.FOUND);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

    }

    public ResponseEntity<List<QuestionModel>> getQuestionsByCategory(String cat) {
        try {
            List<QuestionModel> questionsByCategory = questionDao.findQuestionModelByCategory(cat);
            if(questionsByCategory.isEmpty()) {
                return new ResponseEntity<>(questionsByCategory,HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(questionsByCategory,HttpStatus.FOUND);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<QuestionModel> addQuestion(QuestionModel question) {
        try {
            questionDao.save(question);
            return new ResponseEntity<>(question, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<QuestionModel> editQuestion(Integer questionId, QuestionModel question) {
        try {
            Optional<QuestionModel> questionById = questionDao.findById(questionId);
            if(questionById.isEmpty()) {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
            question.setId(questionId);
            QuestionModel updatedQuestion = questionDao.save(question);
            return new ResponseEntity<>(updatedQuestion, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<?> deleteQuestion(Integer questionId) {
        try{
            Optional<QuestionModel> questionFound = questionDao.findById(questionId);
            if(questionFound.isEmpty()) {
                return new ResponseEntity<>("Question Not Found", HttpStatus.NOT_FOUND);
            }
            questionDao.deleteById(questionId);
            return new ResponseEntity<>("Question Deleted Sucessfully", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
}
