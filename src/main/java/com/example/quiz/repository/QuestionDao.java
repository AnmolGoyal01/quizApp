package com.example.quiz.repository;

import com.example.quiz.model.QuestionModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface QuestionDao extends JpaRepository<QuestionModel, Integer>{
    List<QuestionModel> findQuestionModelByDifficultyLevel(String difficultyLevel);
    List<QuestionModel> findQuestionModelByCategory(String category);
    @Query(value = "Select * from questions q where category= ?category order by RAND() Limit ?numberOfQuestions", nativeQuery = true)
    List<QuestionModel> findQuestionModelByCategoryWithLimit(String category, Integer numberOfQuestions);
}