package nl.haaientanden.eindopdrachtbackendtandartspraktijk.controllers;

import nl.haaientanden.eindopdrachtbackendtandartspraktijk.repositories.TreatmentRepository;
import nl.haaientanden.eindopdrachtbackendtandartspraktijk.services.TreatmentService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TreatmentController {
    public final TreatmentRepository treatmentRepository;
    public final TreatmentService treatmentService;

    public TreatmentController(TreatmentRepository treatmentRepository, TreatmentService treatmentService) {
        this.treatmentRepository = treatmentRepository;
        this.treatmentService = treatmentService;
    }
}
