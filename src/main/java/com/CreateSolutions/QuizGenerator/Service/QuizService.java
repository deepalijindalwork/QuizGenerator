package com.CreateSolutions.QuizGenerator.Service;

import com.CreateSolutions.QuizGenerator.Dao.QuestionDao;
import com.CreateSolutions.QuizGenerator.Dao.QuizDao;
import com.CreateSolutions.QuizGenerator.model.Question;
import com.CreateSolutions.QuizGenerator.model.QuestionWrapper;
import com.CreateSolutions.QuizGenerator.model.Quiz;
import com.CreateSolutions.QuizGenerator.model.Response;
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
    QuizDao quizDao;
    @Autowired
    QuestionDao questionDao;

    public ResponseEntity<String> createQuiz(String sub, int numQ, String title) {
        //return new ResponseEntity<>(quizDao.findAll(), HttpStatus.CREATED);
        List<Question> questions = questionDao.findRandomQuestionByCategory(sub, numQ);
        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(questions);
        quizDao.save(quiz);
        return new ResponseEntity<>("Success", HttpStatus.CREATED);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuiz(Integer id) {
        Optional<Quiz> quiz = quizDao.findById(id);
        List<Question> questionFromDB = quiz.get().getQuestions();
        List<QuestionWrapper> queForUser = new ArrayList<>();

        for (Question q : questionFromDB) {
            QuestionWrapper qw = new QuestionWrapper(q.getID(), q.getQuery(), q.getOption1(), q.getOption2(), q.getOption3(), q.getOption4());
            queForUser.add(qw);
        }

        return new ResponseEntity<>(queForUser, HttpStatus.OK);

    }

    public ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses) {
        Quiz quiz = quizDao.findById(id).get();
        List<Question> questions = quiz.getQuestions();
        int right = 0;
        int i = 0;
        for (Response response : responses) {
            if (response.getResponse().equals(questions.get(i).getRightAnswer()))
                right++;
            i++;
        }
        return new ResponseEntity<>(right, HttpStatus.OK);
    }
}
