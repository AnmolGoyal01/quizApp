package com.example.quiz.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "quiz_table")
public class QuizModel {
    @Id
    private Integer id;
    private String title;
    @ManyToMany
    private List<QuestionModel> questions;
}
