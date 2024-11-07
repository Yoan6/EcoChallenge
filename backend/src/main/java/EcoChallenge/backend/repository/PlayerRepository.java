package EcoChallenge.backend.repository;

import EcoChallenge.backend.entites.Player;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepository extends JpaRepository<Player, Integer> {
}
