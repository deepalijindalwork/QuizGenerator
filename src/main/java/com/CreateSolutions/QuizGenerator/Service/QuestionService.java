package com.CreateSolutions.QuizGenerator.Service;

import com.CreateSolutions.QuizGenerator.Dao.QuestionDao;
import com.CreateSolutions.QuizGenerator.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.source.InvalidConfigurationPropertyValueException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {
    @Autowired
    QuestionDao questionDao;
    public ResponseEntity<List<Question>> getAllQuestions() {
        try {
            return new ResponseEntity<>(questionDao.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<List<Question>> getAllQuestionsBySubject(String subject) {
        try {
            return new ResponseEntity<>(questionDao.findBySubject(subject), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<String> addQuestion(Question question) {
        questionDao.save(question);
        return new ResponseEntity<>("success", HttpStatus.CREATED);
    }

    public String deleteQuestion(Integer ID) {
        questionDao.deleteById(ID);
        return "success";
    }

    public String updateQuestion(Integer id, Question question) {
        Question question1 = questionDao.findById(id)
                .orElseThrow(() -> new InvalidConfigurationPropertyValueException("ID", id, "Invalid ID"));
        question1.setQuery(question.getQuery());
        question1.setSubject(question.getSubject());
        question1.setDifficultyLevel(question.getDifficultyLevel());
        questionDao.save(question1);
        return "success";
    }
}
