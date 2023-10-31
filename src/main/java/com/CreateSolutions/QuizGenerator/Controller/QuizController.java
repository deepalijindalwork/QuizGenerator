package com.CreateSolutions.QuizGenerator.Controller;

import com.CreateSolutions.QuizGenerator.Service.QuizService;
import com.CreateSolutions.QuizGenerator.model.QuestionWrapper;
import com.CreateSolutions.QuizGenerator.model.Quiz;
import com.CreateSolutions.QuizGenerator.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("quiz")
public class QuizController {

    @Autowired
    QuizService quizService;
    @PostMapping("create")
    public ResponseEntity <String> createQuiz (@RequestParam String sub, @RequestParam int numQ, @RequestParam String title) {
        return quizService.createQuiz(sub, numQ, title);
    }

    @GetMapping("get/{id}")
    public ResponseEntity <List<QuestionWrapper>> getQuiz (@PathVariable Integer id) {
        return quizService.getQuiz(id);
    }

    @PostMapping ("submit/{id}")
    public ResponseEntity <Integer> submitQuiz (@PathVariable Integer id, @RequestBody List<Response> responses) {
        return quizService.calculateResult(id, responses);
    }
}
