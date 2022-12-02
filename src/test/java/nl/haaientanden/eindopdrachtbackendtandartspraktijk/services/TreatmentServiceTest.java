package nl.haaientanden.eindopdrachtbackendtandartspraktijk.services;

import nl.haaientanden.eindopdrachtbackendtandartspraktijk.dtos.TreatmentDto;
import nl.haaientanden.eindopdrachtbackendtandartspraktijk.dtos.TreatmentInputDto;
import nl.haaientanden.eindopdrachtbackendtandartspraktijk.exceptions.RecordNotFoundException;
import nl.haaientanden.eindopdrachtbackendtandartspraktijk.models.Treatment;
import nl.haaientanden.eindopdrachtbackendtandartspraktijk.repositories.TreatmentRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static nl.haaientanden.eindopdrachtbackendtandartspraktijk.services.TreatmentService.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TreatmentServiceTest {
    @Mock
    TreatmentRepository mockTreatmentRepository;

    @InjectMocks
    TreatmentService treatmentService;

    TreatmentInputDto treatmentInputDto1;
    TreatmentInputDto treatmentInputDto2;
    TreatmentInputDto treatmentInputDto3;
    List<Treatment> treatments;
    Treatment newTreatment;

    @Captor
    ArgumentCaptor<Treatment> captor;
    Treatment treatment1;
    Treatment treatment2;
    Treatment treatment3;


    @BeforeEach
    void setUp() {

        treatment1 = new Treatment(1L, "C55", "Treatment description 1", 15.22);
        treatment2 = new Treatment(2L, "C56", "Treatment description 2", 16.22);
        treatment3 = new Treatment(3L, "C57", "Treatment description 3", 17.22);
        treatmentInputDto1 = new TreatmentInputDto("C55", "Treatment description 1", 15.22);
        treatmentInputDto2 = new TreatmentInputDto("C56", "Treatment description 2", 16.22);
        treatmentInputDto3 = new TreatmentInputDto("C57", "Treatment description 3", 17.22);
        newTreatment = new Treatment();
        treatments = new ArrayList<>();
        treatments.add(treatment1);
        treatments.add(treatment2);
        treatments.add(treatment3);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testSaveTreatment() {

        when(mockTreatmentRepository.save(any(Treatment.class))).thenReturn(treatment1);

        treatmentService.saveTreatment(treatmentInputDto1);
        verify(mockTreatmentRepository, times(1)).save(captor.capture());
        Treatment treatment = captor.getValue();

        assertEquals(treatment1.getTreatmentCode(), treatment.getTreatmentCode());
        assertEquals(treatment1.getTreatmentDescription(), treatment.getTreatmentDescription());
        assertEquals(treatment1.getTreatmentRate(), treatment.getTreatmentRate());
    }

    @Test
    void testGetTreatments() {
        when(mockTreatmentRepository.findAll()).thenReturn(List.of(treatment1, treatment2, treatment3));

        List<Treatment> treatments = mockTreatmentRepository.findAll();
        List<TreatmentDto> dtos = treatmentService.getTreatments();

        assertEquals(treatments.get(0).getId(), dtos.get(0).getId());
        assertEquals(treatments.get(0).getTreatmentCode(), dtos.get(0).getTreatmentCode());
        assertEquals(treatments.get(0).getTreatmentDescription(), dtos.get(0).getTreatmentDescription());
        assertEquals(treatments.get(0).getTreatmentRate(), dtos.get(0).getTreatmentRate());
        assertEquals(treatments.get(1).getId(), dtos.get(1).getId());
        assertEquals(treatments.get(1).getTreatmentCode(), dtos.get(1).getTreatmentCode());
        assertEquals(treatments.get(1).getTreatmentDescription(), dtos.get(1).getTreatmentDescription());
        assertEquals(treatments.get(1).getTreatmentRate(), dtos.get(1).getTreatmentRate());
        assertEquals(treatments.get(2).getId(), dtos.get(2).getId());
        assertEquals(treatments.get(2).getTreatmentCode(), dtos.get(2).getTreatmentCode());
        assertEquals(treatments.get(2).getTreatmentDescription(), dtos.get(2).getTreatmentDescription());
        assertEquals(treatments.get(2).getTreatmentRate(), dtos.get(2).getTreatmentRate());
    }

    @Test
    void testGetTreatmentById() {
        when(mockTreatmentRepository.findById(1L)).thenReturn(Optional.of(treatment1));

        Treatment treatment = mockTreatmentRepository.findById(1L).get();
        TreatmentDto treatmentDto = treatmentService.getTreatmentById(1L);

        assertEquals(treatment.getTreatmentCode(), treatmentDto.getTreatmentCode());
        assertEquals(treatment.getTreatmentDescription(), treatmentDto.getTreatmentDescription());
        assertEquals(treatment.getTreatmentRate(), treatmentDto.getTreatmentRate());
    }

    @Test
    void testUpdateTreatment() {
        when(mockTreatmentRepository.findById(1L)).thenReturn(Optional.of(treatment1));

        TreatmentInputDto treatmentInputDto = new TreatmentInputDto("C55", "Treatment description 1", 20.00);

        when(mockTreatmentRepository.save(any(Treatment.class))).thenReturn(treatment1);

        treatmentService.updateTreatment(1L, treatmentInputDto);
        verify(mockTreatmentRepository, times(1)).save(captor.capture());
        Treatment updatedTreatment = captor.getValue();

        assertEquals(treatmentInputDto.getTreatmentCode(), updatedTreatment.getTreatmentCode());
        assertEquals(treatmentInputDto.getTreatmentDescription(), updatedTreatment.getTreatmentDescription());
        assertEquals(treatmentInputDto.getTreatmentRate(), updatedTreatment.getTreatmentRate());
    }

    @Test
    void testRecordNotFoundException() {
        assertThrows(RecordNotFoundException.class, () -> treatmentService.getTreatmentById(null));
        assertThrows(RecordNotFoundException.class, () -> treatmentService.updateTreatment(null, treatmentInputDto2));
    }

    @Test
    void testDeleteTreatmentById() {
        treatmentService.deleteTreatmentById(1L);

        verify(mockTreatmentRepository).deleteById(1L);
    }

    @Test
    void testTransferToTreatment() {
        Treatment newTreatment = transferToTreatment(treatmentInputDto3);

        assertEquals(treatmentInputDto3.getTreatmentCode(), newTreatment.getTreatmentCode());
        assertEquals(treatmentInputDto3.getTreatmentDescription(), newTreatment.getTreatmentDescription());
        assertEquals(treatmentInputDto3.getTreatmentRate(), newTreatment.getTreatmentRate());
    }

    @Test
    void testTransferToDto() {
        TreatmentDto newTreatmentDto = transferToDto(treatment1);

        assertEquals(treatment1.getId(), newTreatmentDto.getId());
        assertEquals(treatment1.getTreatmentCode(), newTreatmentDto.getTreatmentCode());
        assertEquals(treatment1.getTreatmentDescription(), newTreatmentDto.getTreatmentDescription());
        assertEquals(treatment1.getTreatmentRate(), newTreatmentDto.getTreatmentRate());
    }

    @Test
    void testTransferTreatmentListToDtoList() {
        List<TreatmentDto> newTreatmentDtoList = transferTreatmentListToDtoList(treatments);

        assertEquals(treatments.get(0).getId(), newTreatmentDtoList.get(0).getId());
        assertEquals(treatments.get(0).getTreatmentCode(), newTreatmentDtoList.get(0).getTreatmentCode());
        assertEquals(treatments.get(0).getTreatmentDescription(), newTreatmentDtoList.get(0).getTreatmentDescription());
        assertEquals(treatments.get(0).getTreatmentRate(), newTreatmentDtoList.get(0).getTreatmentRate());

        assertEquals(treatments.get(1).getId(), newTreatmentDtoList.get(1).getId());
        assertEquals(treatments.get(1).getTreatmentCode(), newTreatmentDtoList.get(1).getTreatmentCode());
        assertEquals(treatments.get(1).getTreatmentDescription(), newTreatmentDtoList.get(1).getTreatmentDescription());
        assertEquals(treatments.get(1).getTreatmentRate(), newTreatmentDtoList.get(1).getTreatmentRate());

        assertEquals(treatments.get(2).getId(), newTreatmentDtoList.get(2).getId());
        assertEquals(treatments.get(2).getTreatmentCode(), newTreatmentDtoList.get(2).getTreatmentCode());
        assertEquals(treatments.get(2).getTreatmentDescription(), newTreatmentDtoList.get(2).getTreatmentDescription());
        assertEquals(treatments.get(2).getTreatmentRate(), newTreatmentDtoList.get(2).getTreatmentRate());
    }
}