package EcoChallenge.backend.controller;

import EcoChallenge.backend.entites.Quiz;
import EcoChallenge.backend.service.QuizService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(path = "quizzes")
public class QuizController {
    private final QuizService quizService;

    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public List<Quiz> getQuizzes() {
        return quizService.getQuizzes();
    }

    @GetMapping(path = "{quizId}")
    public ResponseEntity<?> findQuiz(@PathVariable Integer quizId) {
        try {
            Quiz quizInBdd = quizService.findQuizById(quizId);
            return ResponseEntity.ok(quizInBdd);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
