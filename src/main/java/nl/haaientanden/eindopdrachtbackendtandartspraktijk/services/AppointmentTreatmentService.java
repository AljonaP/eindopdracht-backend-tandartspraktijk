package nl.haaientanden.eindopdrachtbackendtandartspraktijk.services;

import nl.haaientanden.eindopdrachtbackendtandartspraktijk.dtos.AppointmentDto;
import nl.haaientanden.eindopdrachtbackendtandartspraktijk.dtos.TreatmentDto;
import nl.haaientanden.eindopdrachtbackendtandartspraktijk.exceptions.RecordNotFoundException;
import nl.haaientanden.eindopdrachtbackendtandartspraktijk.models.Appointment;
import nl.haaientanden.eindopdrachtbackendtandartspraktijk.models.AppointmentTreatment;
import nl.haaientanden.eindopdrachtbackendtandartspraktijk.models.AppointmentTreatmentKey;
import nl.haaientanden.eindopdrachtbackendtandartspraktijk.models.Treatment;
import nl.haaientanden.eindopdrachtbackendtandartspraktijk.repositories.AppointmentRepository;
import nl.haaientanden.eindopdrachtbackendtandartspraktijk.repositories.AppointmentTreatmentRepository;
import nl.haaientanden.eindopdrachtbackendtandartspraktijk.repositories.TreatmentRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

@Service
public class AppointmentTreatmentService {
    private final AppointmentRepository appointmentRepository;
    private final TreatmentRepository treatmentRepository;
    private final AppointmentTreatmentRepository appointmentTreatmentRepository;

    public AppointmentTreatmentService(AppointmentRepository appointmentRepository,
                                       TreatmentRepository treatmentRepository,
                                       AppointmentTreatmentRepository appointmentTreatmentRepository) {
        this.appointmentRepository = appointmentRepository;
        this.treatmentRepository = treatmentRepository;
        this.appointmentTreatmentRepository = appointmentTreatmentRepository;
    }

    public AppointmentTreatmentKey addAppointmentTreatment(Long appointmentId, Long treatmentId) {
        var appointmentTreatment = new AppointmentTreatment();
        if(!appointmentRepository.existsById(appointmentId)) {
            throw new RecordNotFoundException();
        }
        Appointment appointment = appointmentRepository.findById(appointmentId).orElse(null);
        if(!treatmentRepository.existsById(treatmentId)) {
            throw new RecordNotFoundException();
        }
        Treatment treatment = treatmentRepository.findById(treatmentId).orElse(null);
        appointmentTreatment.setAppointment(appointment);
        appointmentTreatment.setTreatment(treatment);
        AppointmentTreatmentKey id = new AppointmentTreatmentKey(appointmentId, treatmentId);
        appointmentTreatment.setId(id);
        appointmentTreatmentRepository.save(appointmentTreatment);

        return id;
    }

    public Collection<AppointmentDto> getAppointmentTreatmentsByTreatmentId(Long treatmentId) {
        Collection<AppointmentDto> dtos = new HashSet<>();
        Collection<AppointmentTreatment> appointmentTreatments = appointmentTreatmentRepository.findAllByTreatmentId(treatmentId);
        for (AppointmentTreatment appointmentTreatment : appointmentTreatments) {
            Appointment appointment = appointmentTreatment.getAppointment();

            var dto = new AppointmentDto();

            dto.setId(appointment.getId());
            dto.setNameDentist(appointment.getNameDentist());
            dto.setSurnameDentist(appointment.getSurnameDentist());
            dto.setAppointmentDateTime(appointment.getAppointmentDateTime());

            dtos.add(dto);
        }
        return dtos;
    }

    public Collection<TreatmentDto> getAppointmentTreatmentByAppointmentId(Long appointmentId) {
        Collection<TreatmentDto> dtos = new HashSet<>();
        Collection<AppointmentTreatment> appointmentTreatments = appointmentTreatmentRepository.findAllByAppointmentId(appointmentId);
        for (AppointmentTreatment appointmentTreatment : appointmentTreatments) {
            Treatment treatment = appointmentTreatment.getTreatment();

            var dto = new TreatmentDto();

            dto.setId(treatment.getId());
            dto.setTreatmentCode(treatment.getTreatmentCode());
            dto.setTreatmentfDescription(treatment.getTreatmentDescription());
            dto.setTreatmentRate(treatment.getTreatmentRate());

            dtos.add(dto);
        }
        return dtos;
    }

//    public Collection<AppointmentDto> getAllAppointmentsWithoutTreatments() {
//        Collection<AppointmentDto> appointments = appointmentRepository.findAll();
//
//        if (appointmentRepository.findAll().getA < makeDateToday() )
//        Collection<AppointmentTreatment> appointmentTreatments = appointmentTreatmentRepository.findAll();
//        return Collection
//
//    }
//
//    public LocalDateTime makeDateToday() {
//        return LocalDateTime.now();
//    }

}
