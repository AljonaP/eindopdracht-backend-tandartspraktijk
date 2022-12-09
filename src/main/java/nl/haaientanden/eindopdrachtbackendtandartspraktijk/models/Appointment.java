package nl.haaientanden.eindopdrachtbackendtandartspraktijk.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;

import javax.persistence.Entity;
import java.time.LocalDateTime;
import java.util.Collection;

@Entity
@Table(name = "appointments")
public class Appointment {
    @Id
    @GeneratedValue
    private Long id;
    private String nameDentist;
    private String surnameDentist;
    private LocalDateTime appointmentDateTime;
    @OneToOne(cascade = CascadeType.ALL)
    TreatmentRoom treatmentRoom;
    @OneToOne(mappedBy = "appointment")
    Invoice invoice;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "patient_id")
    Patient patient;
    @OneToMany(mappedBy = "appointment")
    @LazyCollection(LazyCollectionOption.FALSE)
    @JsonIgnore
    Collection<AppointmentTreatment> appointmentTreatment;

    public Appointment(String nameDentist,
                       String surnameDentist,
                       LocalDateTime appointmentDateTime) {
        this.nameDentist = nameDentist;
        this.surnameDentist = surnameDentist;
        this.appointmentDateTime = appointmentDateTime;
    }

    public Appointment() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public TreatmentRoom getTreatmentRoom() {
        return treatmentRoom;
    }

    public void setTreatmentRoom(TreatmentRoom treatmentRoom) {
        this.treatmentRoom = treatmentRoom;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Collection<AppointmentTreatment> getAppointmentTreatment() {
        return appointmentTreatment;
    }
}
