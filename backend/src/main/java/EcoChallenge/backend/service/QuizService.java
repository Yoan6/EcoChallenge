package EcoChallenge.backend.service;

import EcoChallenge.backend.entites.Quiz;
import EcoChallenge.backend.repository.QuizRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuizService {
    private final QuizRepository quizRepository;

    public QuizService(QuizRepository quizRepository) {
        this.quizRepository = quizRepository;
    }

    public List<Quiz> getQuizzes() {
        return quizRepository.findAll();
    }

    public Quiz findQuizById(Integer quizId) {
        Optional<Quiz> quizInBdd = quizRepository.findById(quizId);

        if (quizInBdd.isEmpty()) {
            throw new IllegalArgumentException("Quiz non trouvé avec l'id : " + quizId);
        }

        return quizInBdd.get();
    }
}
