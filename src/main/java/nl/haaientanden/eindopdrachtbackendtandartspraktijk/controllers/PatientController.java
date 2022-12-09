package nl.haaientanden.eindopdrachtbackendtandartspraktijk.controllers;

import nl.haaientanden.eindopdrachtbackendtandartspraktijk.dtos.PatientDto;
import nl.haaientanden.eindopdrachtbackendtandartspraktijk.dtos.PatientInputDto;
import nl.haaientanden.eindopdrachtbackendtandartspraktijk.repositories.PatientRepository;
import nl.haaientanden.eindopdrachtbackendtandartspraktijk.services.PatientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static nl.haaientanden.eindopdrachtbackendtandartspraktijk.utils.UtilityMethodes.getErrorMessage;

@RestController
@RequestMapping("/haaientanden/patienten")
public class PatientController {
    private final PatientRepository patientRepository;
    private final PatientService patientService;

    public PatientController(PatientRepository patientRepository, PatientService patientService) {
        this.patientRepository = patientRepository;
        this.patientService = patientService;
    }

    @PostMapping("")
    public ResponseEntity<Object> addPatient(@Valid @RequestBody PatientInputDto patientInputDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {

            return new ResponseEntity<>(getErrorMessage(bindingResult), HttpStatus.BAD_REQUEST);
        }
        PatientDto dto = patientService.savePatient(patientInputDto);

        return ResponseEntity.created(null).body(dto);
    }

    @GetMapping("")
    public ResponseEntity<List<PatientDto>> getAllPatients() {
        List<PatientDto> dtos = patientService.getPatients();

        return ResponseEntity.ok().body(dtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getPatient(@PathVariable(name = "id") Long id) {
        PatientDto dto = patientService.getPatientById(id);

        return ResponseEntity.ok(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updatePatient(@PathVariable(name = "id") Long id, @RequestBody PatientInputDto newPatient) {
        PatientDto dto = patientService.updatePatient(id, newPatient);

        return ResponseEntity.ok().body(dto);
    }

    @DeleteMapping("/{id}")
    public void deletePatient(@PathVariable(name = "id") Long id) {
        patientService.deletePatientById(id);
    }
}
