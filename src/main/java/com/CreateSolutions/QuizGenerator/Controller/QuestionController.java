package com.CreateSolutions.QuizGenerator.Controller;

import com.CreateSolutions.QuizGenerator.model.Question;
import com.CreateSolutions.QuizGenerator.Service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("question")
public class QuestionController {
    @Autowired
    QuestionService questionService;
    @GetMapping("allQuestions")
    public ResponseEntity<List<Question>> getAllQuestions() {
        return questionService.getAllQuestions();
    }
    @GetMapping("subjects/{subject}")
    public ResponseEntity<List<Question>> getAllQuestionsBySubject(@PathVariable String subject) {
        return questionService.getAllQuestionsBySubject(subject);
    }

    @PostMapping("add/")
    public ResponseEntity<String> addQuestion(@RequestBody Question question) {
        return questionService.addQuestion(question);
    }

    @DeleteMapping("delete/{ID}")
    public String deleteQuestion (@PathVariable Integer ID) {
        return questionService.deleteQuestion(ID);
    }

    @PutMapping("update/{ID}")
    public String updateQuestion (@PathVariable Integer ID, @RequestBody Question question) {
        return questionService.updateQuestion(ID, question);
    }
}
