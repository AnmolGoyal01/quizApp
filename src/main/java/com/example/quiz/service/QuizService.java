package com.example.quiz.service;

import com.example.quiz.model.QuestionModel;
import com.example.quiz.model.QuestionWrapper;
import com.example.quiz.model.QuizModel;
import com.example.quiz.model.Response;
import com.example.quiz.repository.QuestionDao;
import com.example.quiz.repository.QuizDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {
    @Autowired
    private QuizDao quizDao;
    @Autowired
    private QuestionDao questionDao;

    public ResponseEntity<?> createQuiz(String category, Integer numberOfQuestions, String title) {
        try{
            List<QuestionModel> quizQuestions = questionDao.findQuestionModelByCategoryWithLimit(category, numberOfQuestions);
            if(quizQuestions.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            QuizModel quizModel = new QuizModel();
            quizModel.setTitle(title);
            quizModel.setQuestions(quizQuestions);
            QuizModel createdQuiz = quizDao.save(quizModel);
            return new ResponseEntity<>(createdQuiz, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<?> getQuizById(Integer quizId) {
        try {
            Optional<QuizModel> quizFetched = quizDao.findById(quizId);
            if(quizFetched.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            List<QuestionModel> quizQuestions = quizFetched.get().getQuestions();
            List<QuestionWrapper> questionForUsers = new ArrayList<>();
            for (QuestionModel q : quizQuestions) {
                QuestionWrapper questionWrapper = new QuestionWrapper(q.getId(), q.getQuestionTitle(), q.getCategory(), q.getOption1(), q.getOption2(), q.getOption3(), q.getOption4(), q.getDifficultyLevel());
                questionForUsers.add(questionWrapper);
            }
            return new ResponseEntity<>(questionForUsers, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }


    public ResponseEntity<?> submitQuiz(Integer quizId, List<Response> responses) {
        Optional<QuizModel> quiz = quizDao.findById(quizId);
        if(quiz.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        QuizModel quizModel = quiz.get();
        List<QuestionModel> quizQuestions = quizModel.getQuestions();
        int score = 0;
        int i=0;
        for(Response r : responses){
            if(r.getResponse().equals(quizQuestions.get(i).getRightAnswer())) score++;
            i++;
        }
        return new ResponseEntity<>(score, HttpStatus.OK);
    }
}
