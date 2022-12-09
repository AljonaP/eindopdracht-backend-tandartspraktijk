package nl.haaientanden.eindopdrachtbackendtandartspraktijk.integrations;

import nl.haaientanden.eindopdrachtbackendtandartspraktijk.controllers.TreatmentControllerTest;
import nl.haaientanden.eindopdrachtbackendtandartspraktijk.dtos.TreatmentDto;
import nl.haaientanden.eindopdrachtbackendtandartspraktijk.dtos.TreatmentInputDto;
import nl.haaientanden.eindopdrachtbackendtandartspraktijk.filter.JwtRequestFilter;
import nl.haaientanden.eindopdrachtbackendtandartspraktijk.models.Treatment;
import nl.haaientanden.eindopdrachtbackendtandartspraktijk.repositories.TreatmentRepository;
import nl.haaientanden.eindopdrachtbackendtandartspraktijk.services.JwtService;
import nl.haaientanden.eindopdrachtbackendtandartspraktijk.services.TreatmentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static nl.haaientanden.eindopdrachtbackendtandartspraktijk.controllers.TreatmentControllerTest.asJsonString;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class TreatmentIntegrationtest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TreatmentService treatmentService;

    @Autowired
    private TreatmentRepository treatmentRepository;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    Treatment treatment1;
    Treatment treatment2;
    TreatmentDto treatmentDto1;
    TreatmentDto treatmentDto2;
    TreatmentInputDto treatmentInputDto1;
    TreatmentInputDto treatmentInputDto2;

    @BeforeEach
    void setUp() {
        treatment1 = new Treatment(1L, "C55", "Treatment description 1", 15.22);
        treatment2 = new Treatment(2L, "C56", "Treatment description 2", 16.22);
        treatmentDto1 = new TreatmentDto(1L, "C55", "Treatment description 1", 15.22);
        treatmentDto2 = new TreatmentDto(2L, "C56", "Treatment description 2", 16.22);
        treatmentInputDto1 = new TreatmentInputDto("C55", "Treatment description 1", 15.22);
        treatmentInputDto2 = new TreatmentInputDto("C56", "Treatment description 2", 16.22);
    }

    @Test
    void testAddTreatment() throws Exception {

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
    void testUpdateTreatment() throws Exception {

        mockMvc.perform(put("/haaientanden/behandelingen/1001")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(treatmentInputDto2)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").value(1001L))
                .andExpect(jsonPath("treatmentCode").value("C56"))
                .andExpect(jsonPath("treatmentDescription").value("Treatment description 2"))
                .andExpect(jsonPath("treatmentRate").value(16.22));
    }
}
