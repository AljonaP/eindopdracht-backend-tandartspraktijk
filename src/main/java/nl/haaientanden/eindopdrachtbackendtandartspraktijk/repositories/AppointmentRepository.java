package nl.haaientanden.eindopdrachtbackendtandartspraktijk.repositories;

import nl.haaientanden.eindopdrachtbackendtandartspraktijk.models.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    List<Appointment> findAllAppointmentsBySurnameDentistEqualsIgnoreCase(String surnameDentist);
}
