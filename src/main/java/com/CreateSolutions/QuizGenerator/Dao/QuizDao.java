package com.CreateSolutions.QuizGenerator.Dao;

import com.CreateSolutions.QuizGenerator.model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizDao extends JpaRepository<Quiz, Integer> {
}
