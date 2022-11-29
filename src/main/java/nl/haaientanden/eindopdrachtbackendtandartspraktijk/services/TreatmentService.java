package nl.haaientanden.eindopdrachtbackendtandartspraktijk.services;

import nl.haaientanden.eindopdrachtbackendtandartspraktijk.dtos.TreatmentDto;
import nl.haaientanden.eindopdrachtbackendtandartspraktijk.dtos.TreatmentInputDto;
import nl.haaientanden.eindopdrachtbackendtandartspraktijk.exceptions.RecordNotFoundException;
import nl.haaientanden.eindopdrachtbackendtandartspraktijk.models.Treatment;
import nl.haaientanden.eindopdrachtbackendtandartspraktijk.repositories.TreatmentRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;


import java.util.ArrayList;
import java.util.List;

@Service
public class TreatmentService {

    private final TreatmentRepository treatmentRepository;

    public TreatmentService(TreatmentRepository treatmentRepository) {

        this.treatmentRepository = treatmentRepository;
    }

    public TreatmentDto saveTreatment(TreatmentInputDto dto) {

        Treatment treatment = transferToTreatment(dto);
        treatmentRepository.save(treatment);

        return transferToDto(treatment);
    }

    public List<TreatmentDto> getTreatments() {

        List<Treatment> treatments = treatmentRepository.findAll();
        List<TreatmentDto> dtos = new ArrayList<>();
        for (Treatment treatment : treatments) {
            dtos.add(transferToDto(treatment));
        }

        return dtos;
    }

    public TreatmentDto getTreatmentById(Long id) {

        if (treatmentRepository.findById(id).isPresent()) {
            Treatment treatment = treatmentRepository.findById(id).get();
            return transferToDto(treatment);
        } else {
            throw new RecordNotFoundException("The entered treatment Id isn't correct or doesn't exist. Search again with another treatment Id.");
        }
    }

    public TreatmentDto updateTreatment(Long id, TreatmentInputDto inputDto) {
        if (treatmentRepository.findById(id).isPresent()) {
            Treatment treatment = treatmentRepository.findById(id).get();
            Treatment treatment1 = transferToTreatment(inputDto);
            treatment1.setTreatmentCode(treatment.getTreatmentCode());

            treatmentRepository.save(treatment1);

            return transferToDto(treatment1);
        } else {
            throw new RecordNotFoundException("Treatment isn't found.");
        }
    }

    public void deleteTreatmentById(@RequestBody Long id) {

        treatmentRepository.deleteById(id);
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

        dto.setId(treatment.getId());
        dto.setTreatmentCode(treatment.getTreatmentCode());
        dto.setTreatmentfDescription(treatment.getTreatmentDescription());
        dto.setTreatmentRate(treatment.getTreatmentRate());

        return dto;
    }


    public static List<TreatmentDto> transferTreatmentListToDtoList(List<Treatment> treatments) {

        List<TreatmentDto> treatmentDtoList = new ArrayList<>();

        for (Treatment treatment : treatments) {
            TreatmentDto dto = transferToDto(treatment);
            treatmentDtoList.add(dto);
        }

        return treatmentDtoList;
    }
}
