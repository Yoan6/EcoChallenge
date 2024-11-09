package EcoChallenge.backend.repository;

import EcoChallenge.backend.entites.Game;
import EcoChallenge.backend.entites.Player;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GameRepository extends JpaRepository<Game, Integer> {}
