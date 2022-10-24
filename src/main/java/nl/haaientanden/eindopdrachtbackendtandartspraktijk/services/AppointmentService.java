package nl.haaientanden.eindopdrachtbackendtandartspraktijk.services;

import nl.haaientanden.eindopdrachtbackendtandartspraktijk.dtos.AppointmentDto;
import nl.haaientanden.eindopdrachtbackendtandartspraktijk.dtos.AppointmentInputDto;
import nl.haaientanden.eindopdrachtbackendtandartspraktijk.models.Appointment;

import nl.haaientanden.eindopdrachtbackendtandartspraktijk.repositories.AppointmentRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AppointmentService {
    private final AppointmentRepository appointmentRepository;


    public AppointmentService(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    public AppointmentDto saveAppointment(AppointmentInputDto dto) {

        Appointment appointment = transferToAppointment(dto);
        appointmentRepository.save(appointment);

        return transferToDto(appointment);
    }

    public List<AppointmentDto> getAppointments() {

        List<Appointment> appointments = appointmentRepository.findAll();
        List<AppointmentDto> dtos = new ArrayList<>();
        for (Appointment appointment : appointments) {
            dtos.add(transferToDto(appointment));
        }
        return dtos;
    }

    public static Appointment transferToAppointment(AppointmentInputDto dto) {

        var appointment = new Appointment();

        appointment.setNameDentist(dto.getNameDentist());
        appointment.setSurnameDentist(dto.getSurnameDentist());
        appointment.setAppointmentDateTime(dto.getAppointmentDateTime());

        return appointment;
    }

    public static AppointmentDto transferToDto(Appointment appointment) {

        AppointmentDto dto = new AppointmentDto();

        dto.setNameDentist(appointment.getNameDentist());
        dto.setSurnameDentist(appointment.getSurnameDentist());
        dto.setAppointmentDateTime(appointment.getAppointmentDateTime());

        return dto;
    }

}
