package nl.haaientanden.eindopdrachtbackendtandartspraktijk.services;

import nl.haaientanden.eindopdrachtbackendtandartspraktijk.dtos.AppointmentDto;
import nl.haaientanden.eindopdrachtbackendtandartspraktijk.dtos.AppointmentInputDto;

import nl.haaientanden.eindopdrachtbackendtandartspraktijk.dtos.PatientDto;
import nl.haaientanden.eindopdrachtbackendtandartspraktijk.dtos.TreatmentRoomDto;
import nl.haaientanden.eindopdrachtbackendtandartspraktijk.exceptions.RecordNotFoundException;
import nl.haaientanden.eindopdrachtbackendtandartspraktijk.models.Appointment;
import nl.haaientanden.eindopdrachtbackendtandartspraktijk.models.Patient;
import nl.haaientanden.eindopdrachtbackendtandartspraktijk.models.TreatmentRoom;
import nl.haaientanden.eindopdrachtbackendtandartspraktijk.repositories.AppointmentRepository;
import nl.haaientanden.eindopdrachtbackendtandartspraktijk.repositories.PatientRepository;
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

    private final PatientRepository patientRepository;
    private final PatientService patientService;

    public AppointmentService(AppointmentRepository appointmentRepository, TreatmentRoomRepository treatmentRoomRepository, TreatmentRoomService treatmentRoomService, PatientRepository patientRepository, PatientService patientService) {
        this.appointmentRepository = appointmentRepository;
        this.treatmentRoomRepository = treatmentRoomRepository;
        this.treatmentRoomService = treatmentRoomService;
        this.patientRepository = patientRepository;
        this.patientService = patientService;
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

    public AppointmentDto assignTreatmentRoomToAppointment(Long appointmentId, Long treatmentRoomId){
        Optional<Appointment> optionalAppointment = appointmentRepository.findById(appointmentId);
        Optional<TreatmentRoom> optionalTreatmentRoom = treatmentRoomRepository.findById(treatmentRoomId);

        if(optionalAppointment.isPresent() && optionalTreatmentRoom.isPresent()) {

            Appointment appointment = optionalAppointment.get();
            TreatmentRoom treatmentRoom = optionalTreatmentRoom.get();
            if ((appointment.getTreatmentRoom() == null) && (!getConnectedToAppointmentTreatmentRoomIds().contains(treatmentRoomId))) {
                appointment.setTreatmentRoom(treatmentRoom);
                appointmentRepository.save(appointment);

                return transferToDto(appointment);
            } else {
                throw new RuntimeException("There is another room already connected to this appointment or chosen room Id is already connected to this or another appointment");
            }

        } else {
            throw new RuntimeException("The requested Appointment isn't found");
        }
    }

    public AppointmentDto assignPatientToAppointment(Long appointmentId, Long patientId) {
        Optional<Appointment> optionalAppointment = appointmentRepository.findById(appointmentId);
        Optional<Patient> optionalPatient = patientRepository.findById(patientId);

        if(optionalAppointment.isPresent() && optionalPatient.isPresent()) {

            Appointment appointment = optionalAppointment.get();
            Patient patient = optionalPatient.get();
            if (appointment.getPatient() == null) {
                appointment.setPatient(patient);
                appointmentRepository.save(appointment);

                return transferToDto(appointment);
            } else {
                throw new RuntimeException("There is another patient already connected to this appointment");
            }
        } else {
            throw new RuntimeException("The requested appointment isn't found.");
        }
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
        if(!(appointment.getPatient() == null)) {
            Patient patient = appointment.getPatient();
            PatientDto patientDto = PatientService.transferToDto(patient);
            dto.setPatientDto(patientDto);
        }
        dto.setId(appointment.getId());
        dto.setNameDentist(appointment.getNameDentist());
        dto.setSurnameDentist(appointment.getSurnameDentist());
        dto.setAppointmentDateTime(appointment.getAppointmentDateTime());

        return dto;
    }

   public ArrayList<Long> getConnectedToAppointmentTreatmentRoomIds() {
       List<Appointment> allAppointments = appointmentRepository.findAll();
       ArrayList<Long> connectedTreatmentRoomIds = new ArrayList<>();
       for (Appointment appointment : allAppointments) {
           if (appointment.getTreatmentRoom() != null) {
               connectedTreatmentRoomIds.add(appointment.getTreatmentRoom().getId());
           }
       }
       return connectedTreatmentRoomIds;
   }

   //in bewerking (Pr.3: to get all the existing appointments for a specific dentist (by surnameDentist)

//   public List<AppointmentDto> getAllAppointmentsBySurnameDentist(String surnameDentist) {
//       List<Appointment> appointmentList = appointmentRepository.findAll(surnameDentist);
//
//       return transferAppointmentListToDtoList(appointmentList);
//   }
//
//   public List<AppointmentDto> transferAppointmentListToDtoList(List<Appointment> appointments){
//       List<AppointmentDto> appointmentDtoList = new ArrayList<>();
//
//       for(Appointment appointment : appointments) {
//           AppointmentDto dto = transferToDto(appointment);
//           appointmentDtoList.add(dto);
//       }
//       return appointmentDtoList;
//   }
   //
}
