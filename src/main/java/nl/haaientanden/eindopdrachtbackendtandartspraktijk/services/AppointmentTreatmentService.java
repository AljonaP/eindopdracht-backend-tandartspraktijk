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

import java.util.Collection;
import java.util.HashSet;

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

    public Collection<AppointmentDto> getAppointmentTreatmentsByTreatmentId(String treatmentId) {
        Collection<AppointmentDto> dtos = new HashSet<>();
        Collection<AppointmentTreatment> appointmentTreatments = appointmentTreatmentRepository.findAllByTreatmentId(treatmentId);
        for (AppointmentTreatment appointmentTreatment : appointmentTreatments) {
            Appointment appointment = appointmentTreatment.getAppointment();
            AppointmentDto dto = new AppointmentDto();

            appointment.setId(dto.getId());
            appointment.setNameDentist(dto.getNameDentist());
            appointment.setSurnameDentist(dto.getSurnameDentist());
            appointment.setAppointmentDateTime(dto.getAppointmentDateTime());

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

            dto.setTreatmentCode(treatment.getTreatmentCode());
            dto.setTreatmentfDescription(treatment.getTreatmentDescription());
            dto.setTreatmentRate(treatment.getTreatmentRate());

            dtos.add(dto);
        }
        return dtos;
    }

    public AppointmentTreatmentKey addAppointmentTreatment(Long appointmentId, String treatmentId) {
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
}
