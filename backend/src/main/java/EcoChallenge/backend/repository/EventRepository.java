package EcoChallenge.backend.repository;

import EcoChallenge.backend.entites.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Integer> {
}
