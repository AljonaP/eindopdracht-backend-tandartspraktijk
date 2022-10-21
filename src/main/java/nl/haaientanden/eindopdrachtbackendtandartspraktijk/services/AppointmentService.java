package nl.haaientanden.eindopdrachtbackendtandartspraktijk.services;

import nl.haaientanden.eindopdrachtbackendtandartspraktijk.dtos.AppointmentDto;
import nl.haaientanden.eindopdrachtbackendtandartspraktijk.dtos.AppointmentInputDto;
import nl.haaientanden.eindopdrachtbackendtandartspraktijk.dtos.TreatmentDto;
import nl.haaientanden.eindopdrachtbackendtandartspraktijk.dtos.TreatmentInputDto;
import nl.haaientanden.eindopdrachtbackendtandartspraktijk.models.Appointment;
import nl.haaientanden.eindopdrachtbackendtandartspraktijk.models.Treatment;
import nl.haaientanden.eindopdrachtbackendtandartspraktijk.repositories.AppointmentRepository;
import org.springframework.stereotype.Service;

@Service
public class AppointmentService {
    public final AppointmentRepository appointmentRepository;


    public AppointmentService(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    public static Appointment transferToAppointment(AppointmentInputDto dto) {

        var appointment = new Appointment();

        appointment.setNameDentist(dto.getNameDentist());
        appointment.setSurnameDentist(dto.getSurnameDentist());
        appointment.setAppointmentDate(dto.getAppointmentDate());
        appointment.setAppointmentTime(dto.getAppointmentTime());

        return appointment;
    }

    public static AppointmentDto transferToDto(Appointment appointment) {

        AppointmentDto dto = new AppointmentDto();

        dto.setNameDentist(appointment.getNameDentist());
        dto.setSurnameDentist(appointment.getSurnameDentist());
        dto.setAppointmentDate(appointment.getAppointmentDate());
        dto.setAppointmentTime(appointment.getAppointmentTime());

        return dto;
    }

}
