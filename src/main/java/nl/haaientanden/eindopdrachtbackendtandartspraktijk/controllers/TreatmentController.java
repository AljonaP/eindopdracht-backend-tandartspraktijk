package nl.haaientanden.eindopdrachtbackendtandartspraktijk.controllers;

import nl.haaientanden.eindopdrachtbackendtandartspraktijk.dtos.TreatmentDto;
import nl.haaientanden.eindopdrachtbackendtandartspraktijk.dtos.TreatmentInputDto;
import nl.haaientanden.eindopdrachtbackendtandartspraktijk.repositories.TreatmentRepository;
import nl.haaientanden.eindopdrachtbackendtandartspraktijk.services.TreatmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/haaientanden/behandelingen")
public class TreatmentController {
    public final TreatmentRepository treatmentRepository;
    public final TreatmentService treatmentService;

    public TreatmentController(TreatmentRepository treatmentRepository, TreatmentService treatmentService) {
        this.treatmentRepository = treatmentRepository;
        this.treatmentService = treatmentService;
    }

    @GetMapping("")
    public ResponseEntity<List<TreatmentDto>> getAllTreatments() {
        return ResponseEntity.ok().body(treatmentService.getTreatments());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getTreatment(@PathVariable(name = "id") String id) {
        return ResponseEntity.ok(treatmentService.getTreatmentById(id));
    }

    @PostMapping("")
    public ResponseEntity<Object> addTreatment(@Valid @RequestBody TreatmentInputDto treatmentInputDto) {
        TreatmentDto dto = treatmentService.saveTreatment(treatmentInputDto);
        return ResponseEntity.created(null).body(dto);
    }





}
