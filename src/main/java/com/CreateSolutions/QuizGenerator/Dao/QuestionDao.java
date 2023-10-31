package com.CreateSolutions.QuizGenerator.Dao;

import com.CreateSolutions.QuizGenerator.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionDao extends JpaRepository<Question, Integer> {
    List<Question> findBySubject (String subject);
    @Query(value = "SELECT * FROM question q WHERE q.subject=:sub ORDER BY RANDOM() LIMIT :numQ", nativeQuery = true)

    List<Question> findRandomQuestionByCategory(String sub, Integer numQ);
}
