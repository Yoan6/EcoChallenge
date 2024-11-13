package EcoChallenge.backend.repository;

import EcoChallenge.backend.entites.Game;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepository extends JpaRepository<Game, Integer> {}
