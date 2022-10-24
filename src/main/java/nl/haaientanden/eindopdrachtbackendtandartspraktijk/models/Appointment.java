package nl.haaientanden.eindopdrachtbackendtandartspraktijk.models;

import javax.persistence.*;

import javax.persistence.Entity;
import java.time.LocalDateTime;

@Entity
@Table(name="appointments")
public class Appointment {

    @Id
    @GeneratedValue
    private Long id;
    private String nameDentist;
    private String surnameDentist;
    private LocalDateTime appointmentDateTime;


    public Appointment(String nameDentist, String surnameDentist, LocalDateTime appointmentDateTime) {
        this.nameDentist = nameDentist;
        this.surnameDentist = surnameDentist;
        this.appointmentDateTime = appointmentDateTime;
    }

    public Appointment() {
    }

    public String getNameDentist() {
        return nameDentist;
    }

    public void setNameDentist(String nameDentist) {
        this.nameDentist = nameDentist;
    }

    public String getSurnameDentist() {
        return surnameDentist;
    }

    public void setSurnameDentist(String surnameDentist) {
        this.surnameDentist = surnameDentist;
    }

    public LocalDateTime getAppointmentDateTime() {
        return appointmentDateTime;
    }

    public void setAppointmentDateTime(LocalDateTime appointmentDateTime) {
        this.appointmentDateTime = appointmentDateTime;
    }
}
