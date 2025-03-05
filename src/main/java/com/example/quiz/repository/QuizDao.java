package com.example.quiz.repository;

import com.example.quiz.model.QuizModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizDao extends JpaRepository<QuizModel, Integer> {

}
