package nl.haaientanden.eindopdrachtbackendtandartspraktijk.services;

import nl.haaientanden.eindopdrachtbackendtandartspraktijk.dtos.TreatmentDto;
import nl.haaientanden.eindopdrachtbackendtandartspraktijk.dtos.TreatmentInputDto;
import nl.haaientanden.eindopdrachtbackendtandartspraktijk.models.Treatment;
import nl.haaientanden.eindopdrachtbackendtandartspraktijk.repositories.TreatmentRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TreatmentService {
    private final TreatmentRepository treatmentRepository;

    public TreatmentService(TreatmentRepository treatmentRepository) {
        this.treatmentRepository = treatmentRepository;
    }

    public List<TreatmentDto> getTreatments() {
        List<Treatment> treatments = treatmentRepository.findAll();
        List<TreatmentDto> dtos = new ArrayList<>();
        for (Treatment treatment : treatments) {
            dtos.add(transferToDto(treatment));
        }
        return dtos;
    }

    public TreatmentDto saveTreatment(TreatmentInputDto dto) {
        Treatment treatment = transferToTreatment(dto);
        treatmentRepository.save(treatment);
        return transferToDto(treatment);
    }

    public static Treatment transferToTreatment(TreatmentInputDto dto) {
        var treatment = new Treatment();

        treatment.setTreatmentCode(dto.getTreatmentCode());
        treatment.setTreatmentDescription(dto.getTreatmentDescription());
        treatment.setTreatmentRate(dto.getTreatmentRate());

        return treatment;
    }

    public static TreatmentDto transferToDto(Treatment treatment) {
        TreatmentDto dto = new TreatmentDto();

        dto.setTreatmentCode(treatment.getTreatmentCode());
        dto.setTreatmentfDescription(treatment.getTreatmentDescription());
        dto.setTreatmentRate(treatment.getTreatmentRate());

        return dto;
    }
}
