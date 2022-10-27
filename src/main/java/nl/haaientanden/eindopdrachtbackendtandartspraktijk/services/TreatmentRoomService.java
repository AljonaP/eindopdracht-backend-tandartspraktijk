package nl.haaientanden.eindopdrachtbackendtandartspraktijk.services;

import nl.haaientanden.eindopdrachtbackendtandartspraktijk.repositories.TreatmentRoomRepository;
import org.springframework.stereotype.Service;

@Service
public class TreatmentRoomService {
    private final TreatmentRoomRepository treatmentRoomRepository;

    public TreatmentRoomService(TreatmentRoomRepository treatmentRoomRepository) {
        this.treatmentRoomRepository = treatmentRoomRepository;
    }
}
