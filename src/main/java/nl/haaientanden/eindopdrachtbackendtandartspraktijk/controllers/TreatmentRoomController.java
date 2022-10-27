package nl.haaientanden.eindopdrachtbackendtandartspraktijk.controllers;


import nl.haaientanden.eindopdrachtbackendtandartspraktijk.repositories.TreatmentRoomRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("haaientanden/behandelkamers")
public class TreatmentRoomController {
    private final TreatmentRoomRepository treatmentRoomRepository;
    private final TreatmentRoomService treatmentRoomService;

    public TreatmentRoomController(TreatmentRoomRepository treatmentRoomRepository, TreatmentRoomService treatmentRoomService) {
        this.treatmentRoomRepository = treatmentRoomRepository;
        this.treatmentRoomService = treatmentRoomService;
    }
}
