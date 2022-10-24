package nl.haaientanden.eindopdrachtbackendtandartspraktijk.controllers;

import nl.haaientanden.eindopdrachtbackendtandartspraktijk.repositories.PatientRepository;
import nl.haaientanden.eindopdrachtbackendtandartspraktijk.services.PatientService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PatientController {
    private final PatientRepository patientRepository;
    private final PatientService patientService;

    public PatientController(PatientRepository patientRepository, PatientService patientService) {
        this.patientRepository = patientRepository;
        this.patientService = patientService;
    }
}
