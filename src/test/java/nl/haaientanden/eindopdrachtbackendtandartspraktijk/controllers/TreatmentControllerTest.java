package nl.haaientanden.eindopdrachtbackendtandartspraktijk.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import nl.haaientanden.eindopdrachtbackendtandartspraktijk.dtos.TreatmentDto;
import nl.haaientanden.eindopdrachtbackendtandartspraktijk.dtos.TreatmentInputDto;
import nl.haaientanden.eindopdrachtbackendtandartspraktijk.filter.JwtRequestFilter;
import nl.haaientanden.eindopdrachtbackendtandartspraktijk.models.Treatment;
import nl.haaientanden.eindopdrachtbackendtandartspraktijk.repositories.TreatmentRepository;
import nl.haaientanden.eindopdrachtbackendtandartspraktijk.services.JwtService;
import nl.haaientanden.eindopdrachtbackendtandartspraktijk.services.TreatmentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(TreatmentController.class)
@AutoConfigureMockMvc(addFilters = false)
@MockitoSettings(strictness = Strictness.LENIENT)
public class TreatmentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TreatmentService treatmentService;

    @MockBean
    private TreatmentRepository mockTreatmentRepository;

    @MockBean
    private JwtService jwtService;

    @MockBean
    private JwtRequestFilter jwtRequestFilter;

    Treatment treatment1;
    Treatment treatment2;
    TreatmentDto treatmentDto1;
    TreatmentDto treatmentDto2;
    TreatmentInputDto treatmentInputDto1;
    TreatmentInputDto treatmentInputDto2;
    TreatmentInputDto treatmentInputDto3;

    @BeforeEach
    void setUp() {
        treatment1 = new Treatment(1L, "C55", "Treatment description 1", 15.22);
        treatment2 = new Treatment(2L, "C56", "Treatment description 2", 16.22);
        treatmentDto1 = new TreatmentDto(1L, "C55", "Treatment description 1", 15.22);
        treatmentDto2 = new TreatmentDto(2L, "C56", "Treatment description 2", 16.22);
        treatmentInputDto1 = new TreatmentInputDto("C55", "Treatment description 1", 15.22);
        treatmentInputDto2 = new TreatmentInputDto("C56", "Treatment description 2", 16.22);
        treatmentInputDto3 = new TreatmentInputDto("C56", "Treatment description 2", -16.22);
    }

    @Test
    void testAddTreatment() throws Exception {
        given(treatmentService.saveTreatment(any(TreatmentInputDto.class))).willReturn(treatmentDto1);

        mockMvc.perform(post("/haaientanden/behandelingen")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(treatmentInputDto1)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("id").value(1L))
                .andExpect(jsonPath("treatmentCode").value("C55"))
                .andExpect(jsonPath("treatmentDescription").value("Treatment description 1"))
                .andExpect(jsonPath("treatmentRate").value(15.22));
    }

    @Test
    void testAddTreatmentWithBadRequest() throws Exception {
        mockMvc.perform(post("/haaientanden/behandelingen")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(treatmentInputDto3)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testGetAllTreatments() throws Exception {
        given(treatmentService.getTreatments()).willReturn(List.of(treatmentDto1, treatmentDto2));

        mockMvc.perform(get("/haaientanden/behandelingen"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].treatmentCode").value("C55"))
                .andExpect(jsonPath("$[0].treatmentDescription").value("Treatment description 1"))
                .andExpect(jsonPath("$[0].treatmentRate").value(15.22))
                .andExpect(jsonPath("$[1].id").value(2L))
                .andExpect(jsonPath("$[1].treatmentCode").value("C56"))
                .andExpect(jsonPath("$[1].treatmentDescription").value("Treatment description 2"))
                .andExpect(jsonPath("$[1].treatmentRate").value(16.22));
    }

    @Test
    void testGetTreatment() throws Exception {
        given(treatmentService.getTreatmentById(1L)).willReturn(treatmentDto1);

        mockMvc.perform(get("/haaientanden/behandelingen/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").value(1L))
                .andExpect(jsonPath("treatmentCode").value("C55"))
                .andExpect(jsonPath("treatmentDescription").value("Treatment description 1"))
                .andExpect(jsonPath("treatmentRate").value(15.22));
    }

    @Test
    void testUpdateTreatment() throws Exception {
        given(treatmentService.updateTreatment(any(Long.class), any(TreatmentInputDto.class))).willReturn(treatmentDto2);

        mockMvc.perform(put("/haaientanden/behandelingen/2")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(treatmentInputDto2)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").value(2L))
                .andExpect(jsonPath("treatmentCode").value("C56"))
                .andExpect(jsonPath("treatmentDescription").value("Treatment description 2"))
                .andExpect(jsonPath("treatmentRate").value(16.22));
    }

    @Test
    void testDeleteTreatment() throws Exception {
        mockMvc.perform(delete("/haaientanden/behandelingen/1"))
                .andExpect(status().isOk());
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}