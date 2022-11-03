package nl.haaientanden.eindopdrachtbackendtandartspraktijk.controllers;

import nl.haaientanden.eindopdrachtbackendtandartspraktijk.services.AppointmentTreatmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/haaientanden/behandelingen-bij-afspraken")
public class AppointmentTreatmentController {
    private final AppointmentTreatmentService appointmentTreatmentService;


    public AppointmentTreatmentController(AppointmentTreatmentService appointmentTreatmentService) {
        this.appointmentTreatmentService = appointmentTreatmentService;
    }

    @PostMapping("/{appointmentId}/{treatmentId}")
    public void addAppointmentTreatment(@PathVariable("appointmentId") Long appointmentId, @PathVariable("treatmentId") Long treatmentId) {
        appointmentTreatmentService.addAppointmentTreatment(appointmentId, treatmentId);
    }

    @GetMapping("/afspraken/{id}")
    public ResponseEntity<Object> getTreatmentsByAppointmentId(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(appointmentTreatmentService.getAppointmentTreatmentByAppointmentId(id));
    }

    @GetMapping("/behandelingen/{id}")
    public ResponseEntity<Object> getAppointmentsByTreatmentsId(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(appointmentTreatmentService.getAppointmentTreatmentsByTreatmentId(id));
    }
}
