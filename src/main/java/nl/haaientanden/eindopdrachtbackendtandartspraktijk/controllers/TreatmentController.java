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

    @PostMapping("")
    public ResponseEntity<Object> addTreatment(@Valid @RequestBody TreatmentInputDto treatmentInputDto) {
        TreatmentDto dto = treatmentService.saveTreatment(treatmentInputDto);
        return ResponseEntity.created(null).body(dto);
    }

    @GetMapping("")
    public ResponseEntity<List<TreatmentDto>> getAllTreatments() {
        return ResponseEntity.ok().body(treatmentService.getTreatments());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getTreatment(@PathVariable(name = "id") String id) {
        return ResponseEntity.ok(treatmentService.getTreatmentById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateTreatment(@PathVariable(name="id") String id, @RequestBody TreatmentInputDto newTreatment) {
        TreatmentDto dto = treatmentService.updateTreatment(id, newTreatment);

        return ResponseEntity.ok().body(dto);
    }
    @DeleteMapping("/{id}")
    public void deleteTreatment(@PathVariable(name = "id") String id) {
        treatmentService.deleteTreatmentById(id);
    }





}
