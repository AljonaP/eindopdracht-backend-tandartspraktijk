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

}


