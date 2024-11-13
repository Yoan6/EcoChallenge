package EcoChallenge.backend.repository;

import EcoChallenge.backend.entites.Choice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChoiceRepository extends JpaRepository<Choice, Integer> {
}
