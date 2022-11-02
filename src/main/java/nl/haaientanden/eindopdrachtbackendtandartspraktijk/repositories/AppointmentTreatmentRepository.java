package nl.haaientanden.eindopdrachtbackendtandartspraktijk.repositories;

import nl.haaientanden.eindopdrachtbackendtandartspraktijk.models.AppointmentTreatment;
import nl.haaientanden.eindopdrachtbackendtandartspraktijk.models.AppointmentTreatmentKey;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface AppointmentTreatmentRepository extends JpaRepository<AppointmentTreatment, AppointmentTreatmentKey> {
    Collection<AppointmentTreatment> findAllByAppointmentId(Long Id);
    Collection<AppointmentTreatment> findAllByTreatmentId(String Id);
}
