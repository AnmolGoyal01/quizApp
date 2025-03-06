package com.example.quiz.repository;

import com.example.quiz.model.QuestionModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface QuestionDao extends JpaRepository<QuestionModel, Integer>{
    List<QuestionModel> findQuestionModelByDifficultyLevel(String difficultyLevel);
    List<QuestionModel> findQuestionModelByCategory(String category);
    @Query("Select q from QuestionModel q where q.category =:cat order by RAND() Limit :num")
    List<QuestionModel> findQuestionModelByCategoryWithLimit(@Param("cat") String category, @Param("num") Integer numberOfQuestions);
}