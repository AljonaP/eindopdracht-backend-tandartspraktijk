package nl.haaientanden.eindopdrachtbackendtandartspraktijk.controllers;

import nl.haaientanden.eindopdrachtbackendtandartspraktijk.dtos.AppointmentDto;
import nl.haaientanden.eindopdrachtbackendtandartspraktijk.dtos.AppointmentInputDto;
import nl.haaientanden.eindopdrachtbackendtandartspraktijk.repositories.AppointmentRepository;
import nl.haaientanden.eindopdrachtbackendtandartspraktijk.services.AppointmentService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import java.util.List;

import static nl.haaientanden.eindopdrachtbackendtandartspraktijk.utils.UtilityMethodes.getErrorMessage;

@RestController
@RequestMapping("/haaientanden/afspraken")
public class AppointmentController {
    private final AppointmentRepository appointmentRepository;
    private final AppointmentService appointmentService;

    public AppointmentController(AppointmentRepository appointmentRepository, AppointmentService appointmentService) {
        this.appointmentRepository = appointmentRepository;
        this.appointmentService = appointmentService;
    }

    @PostMapping("")
    public ResponseEntity<Object> addTreatment(@Valid @RequestBody AppointmentInputDto appointmentInputDto, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return new ResponseEntity<>(getErrorMessage(bindingResult), HttpStatus.BAD_REQUEST);
        }
        AppointmentDto dto = appointmentService.saveAppointment(appointmentInputDto);
        return ResponseEntity.created(null).body(dto);
    }

    @GetMapping("")
    public ResponseEntity<List<AppointmentDto>> getAllAppointments() {
        return ResponseEntity.ok().body(appointmentService.getAppointments());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getAppointment(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(appointmentService.getAppointmentById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateAppointment(@PathVariable(name="id") Long id, @RequestBody AppointmentInputDto newAppointment) {
        AppointmentDto dto = appointmentService.updateAppointment(id, newAppointment);

        return ResponseEntity.ok().body(dto);
    }

    @PutMapping("/{appointmentId}/behandelkamers/{behandelKamerId}")
    public ResponseEntity<Object> assignTreatmentRoomToAppointment(@PathVariable Long appointmentId, @PathVariable Long behandelKamerId ){
        AppointmentDto appointmentDto = appointmentService.assignTreatmentRoomToAppointment(appointmentId, behandelKamerId);
        return ResponseEntity.ok(appointmentDto);
    }

    @PutMapping("/{appointmentId}/patienten/{patientId}")
    public ResponseEntity<Object> assignPatientToAppointment(@PathVariable Long appointmentId, @PathVariable Long patientId) {
        AppointmentDto appointmentDto = appointmentService.assignPatientToAppointment(appointmentId, patientId);
        return ResponseEntity.ok(appointmentDto);
    }

    @DeleteMapping("/{id}")
    public void deleteAppointment(@PathVariable(name = "id") Long id) {
        appointmentService.deleteAppointmentById(id);
    }





}


