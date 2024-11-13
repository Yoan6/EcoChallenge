package EcoChallenge.backend.controller;

import EcoChallenge.backend.entites.Event;
import EcoChallenge.backend.service.EventService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(path = "events")
public class EventController {
    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public List<Event> getEvents() {
        return eventService.getEvents();
    }

    @GetMapping(path = "{eventId}")
    public ResponseEntity<?> getEvent(@PathVariable Integer eventId) {
        try {
            Event eventInBdd = eventService.findEventById(eventId);
            return ResponseEntity.ok(eventInBdd);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
