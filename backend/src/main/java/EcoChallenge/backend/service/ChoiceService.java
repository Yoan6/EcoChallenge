package EcoChallenge.backend.service;

import EcoChallenge.backend.entites.Choice;
import EcoChallenge.backend.repository.ChoiceRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChoiceService {
    private final ChoiceRepository choiceRepository;

    public ChoiceService(ChoiceRepository choiceRepository) {
        this.choiceRepository = choiceRepository;
    }

    public List<Choice> getChoices() {
        return choiceRepository.findAll();
    }

    public Choice findChoiceById(Integer choiceId) {
        Optional<Choice> choiceInBdd = this.choiceRepository.findById(choiceId);

        if (choiceInBdd.isEmpty()) {
            throw new IllegalArgumentException("Choix non trouvé avec l'ID : " + choiceId);
        }

        return choiceInBdd.get();
    }
}
