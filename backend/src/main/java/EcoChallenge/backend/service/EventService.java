package EcoChallenge.backend.service;

import EcoChallenge.backend.entites.Event;
import EcoChallenge.backend.repository.EventRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventService {
    private final EventRepository eventRepository;

    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public List<Event> getEvents() {
        return this.eventRepository.findAll();
    }

    public Event findEventById(Integer eventId) {
        Optional<Event> eventInBdd = this.eventRepository.findById(eventId);

        if (eventInBdd.isEmpty()) {
            throw new IllegalArgumentException("Evènement non trouvée avec l'ID : " + eventId);
        }

        return eventInBdd.get();
    }
}
