package nl.haaientanden.eindopdrachtbackendtandartspraktijk.dtos;

import java.sql.Time;
import java.time.LocalDate;

public class AppointmentDto {
    private String nameDentist;
    private String surnameDentist;
    private LocalDate appointmentDate;
    private Time appointmentTime;

    public AppointmentDto(String nameDentist, String surnameDentist, LocalDate appointmentDate, Time appointmentTime) {
        this.nameDentist = nameDentist;
        this.surnameDentist = surnameDentist;
        this.appointmentDate = appointmentDate;
        this.appointmentTime = appointmentTime;
    }

    public AppointmentDto() {
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

    public LocalDate getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(LocalDate appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public Time getAppointmentTime() {
        return appointmentTime;
    }

    public void setAppointmentTime(Time appointmentTime) {
        this.appointmentTime = appointmentTime;
    }
}