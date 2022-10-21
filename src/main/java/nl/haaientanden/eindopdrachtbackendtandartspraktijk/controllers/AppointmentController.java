package nl.haaientanden.eindopdrachtbackendtandartspraktijk.controllers;

import nl.haaientanden.eindopdrachtbackendtandartspraktijk.repositories.AppointmentRepository;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppointmentController {
    public final AppointmentRepository appointmentRepository;

    public final AppointmentService appointmentService;

    public AppointmentController(AppointmentRepository appointmentRepository, AppointmentService appointmentService) {
        this.appointmentRepository = appointmentRepository;
        this.appointmentService = appointmentService;
    }
}
