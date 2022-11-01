package nl.haaientanden.eindopdrachtbackendtandartspraktijk.services;

import nl.haaientanden.eindopdrachtbackendtandartspraktijk.dtos.AppointmentDto;
import nl.haaientanden.eindopdrachtbackendtandartspraktijk.dtos.AppointmentInputDto;

import nl.haaientanden.eindopdrachtbackendtandartspraktijk.dtos.TreatmentRoomDto;
import nl.haaientanden.eindopdrachtbackendtandartspraktijk.exceptions.RecordNotFoundException;
import nl.haaientanden.eindopdrachtbackendtandartspraktijk.models.Appointment;
import nl.haaientanden.eindopdrachtbackendtandartspraktijk.models.TreatmentRoom;
import nl.haaientanden.eindopdrachtbackendtandartspraktijk.repositories.AppointmentRepository;
import nl.haaientanden.eindopdrachtbackendtandartspraktijk.repositories.TreatmentRoomRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AppointmentService {
    private final AppointmentRepository appointmentRepository;
    private final TreatmentRoomRepository treatmentRoomRepository;
    private final TreatmentRoomService treatmentRoomService;


    public AppointmentService(AppointmentRepository appointmentRepository, TreatmentRoomRepository treatmentRoomRepository, TreatmentRoomService treatmentRoomService) {
        this.appointmentRepository = appointmentRepository;
        this.treatmentRoomRepository = treatmentRoomRepository;
        this.treatmentRoomService = treatmentRoomService;
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
        if(!(appointment.getTreatmentRoom() == null)) {
            TreatmentRoom treatmentRoom = appointment.getTreatmentRoom();
            TreatmentRoomDto treatmentRoomDto = TreatmentRoomService.transferToDto(treatmentRoom);
            dto.setTreatmentRoomDto(treatmentRoomDto);
        }
        dto.setId(appointment.getId());
        dto.setNameDentist(appointment.getNameDentist());
        dto.setSurnameDentist(appointment.getSurnameDentist());
        dto.setAppointmentDateTime(appointment.getAppointmentDateTime());

        return dto;
    }

    public AppointmentDto assignTreatmentRoomToAppointment(Long appointmentId, Long treatmentRoomId){
        Optional<Appointment> optionalAppointment = appointmentRepository.findById(appointmentId);
        Optional<TreatmentRoom> optionalTreatmentRoom = treatmentRoomRepository.findById(treatmentRoomId);

        if(optionalAppointment.isPresent() && optionalTreatmentRoom.isPresent()) {

            Appointment appointment = optionalAppointment.get();
            TreatmentRoom treatmentRoom = optionalTreatmentRoom.get();
            if (appointment.getTreatmentRoom() == null) {
                appointment.setTreatmentRoom(treatmentRoom);
                appointmentRepository.save(appointment);

                return transferToDto(appointment);
            }
            throw new RuntimeException("There is another room with Id " + appointment.getTreatmentRoom().getId() + " already connected to this appointment");

        } else {
            throw new RuntimeException("The requested Appointment isn't found");
        }
    }

}
