package EcoChallenge.backend.controller;

import EcoChallenge.backend.entites.Choice;
import EcoChallenge.backend.service.ChoiceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(path = "choices")
public class ChoiceController {
    private final ChoiceService choiceService;

    public ChoiceController(ChoiceService choiceService) {
        this.choiceService = choiceService;
    }

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public List<Choice> getChoices() {
        return choiceService.getChoices();
    }

    @GetMapping(path = "{choiceId}")
    public ResponseEntity<?> getChoice(@PathVariable Integer choiceId) {
        try {
            Choice choiceInBdd = choiceService.findChoiceById(choiceId);
            return ResponseEntity.ok(choiceInBdd);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
