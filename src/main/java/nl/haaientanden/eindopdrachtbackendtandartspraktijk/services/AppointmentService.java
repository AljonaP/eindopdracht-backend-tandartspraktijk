package nl.haaientanden.eindopdrachtbackendtandartspraktijk.services;

import nl.haaientanden.eindopdrachtbackendtandartspraktijk.dtos.AppointmentDto;
import nl.haaientanden.eindopdrachtbackendtandartspraktijk.dtos.AppointmentInputDto;

import nl.haaientanden.eindopdrachtbackendtandartspraktijk.exceptions.RecordNotFoundException;
import nl.haaientanden.eindopdrachtbackendtandartspraktijk.models.Appointment;
import nl.haaientanden.eindopdrachtbackendtandartspraktijk.repositories.AppointmentRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

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

    public AppointmentDto getAppointmentById(Long id) {

        if(appointmentRepository.findById(id).isPresent()){
            Appointment appointment = appointmentRepository.findById(id).get();
            return transferToDto(appointment);
        } else {
            throw new RecordNotFoundException("The entered value isn't correct or doesn't exist. Search again with another value.");
        }
    }

    public AppointmentDto updateAppointment(Long id, AppointmentInputDto inputDto) {
        if(appointmentRepository.findById(id).isPresent()) {
            Appointment appointment = appointmentRepository.findById(id).get();
            Appointment appointment1 = transferToAppointment(inputDto);
            appointment1.setId(appointment.getId());

            appointmentRepository.save(appointment1);

            return transferToDto(appointment1);
        } else {
            throw new RecordNotFoundException("geen treatment is gevonden");
        }
    }

    public void deleteAppointmentById(@RequestBody Long id) {
        appointmentRepository.deleteById(id);
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
