package nl.haaientanden.eindopdrachtbackendtandartspraktijk.services;

import nl.haaientanden.eindopdrachtbackendtandartspraktijk.dtos.TreatmentDto;
import nl.haaientanden.eindopdrachtbackendtandartspraktijk.dtos.TreatmentInputDto;
import nl.haaientanden.eindopdrachtbackendtandartspraktijk.models.Treatment;
import nl.haaientanden.eindopdrachtbackendtandartspraktijk.repositories.TreatmentRepository;
import org.springframework.stereotype.Service;

@Service
public class TreatmentService {
    private final TreatmentRepository treatmentRepository;

    public TreatmentService(TreatmentRepository treatmentRepository) {
        this.treatmentRepository = treatmentRepository;
    }

    public static Treatment fromDtoToTreatment(TreatmentInputDto dto) {
        var treatment = new Treatment();

        treatment.setTreatmentCode(dto.getTreatmentCode());
        treatment.setTreatmentfDescription(dto.getTreatmentfDescription());
        treatment.setTreatmentRate(dto.getTreatmentRate());

        return treatment;
    }

    public static TreatmentDto fromTreatmentToDto(Treatment treatment) {
        TreatmentDto dto = new TreatmentDto();

        dto.setTreatmentCode(treatment.getTreatmentCode());
        dto.setTreatmentfDescription(treatment.getTreatmentfDescription());
        dto.setTreatmentRate(treatment.getTreatmentRate());

        return dto;
    }
}
