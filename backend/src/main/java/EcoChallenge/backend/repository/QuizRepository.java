package EcoChallenge.backend.repository;

import EcoChallenge.backend.entites.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizRepository extends JpaRepository<Quiz, Integer> {
}
