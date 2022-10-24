package nl.haaientanden.eindopdrachtbackendtandartspraktijk.dtos;

import java.time.LocalDateTime;

public class AppointmentDto {
    private Long id;
    private String nameDentist;
    private String surnameDentist;
    private LocalDateTime appointmentDateTime;

    public AppointmentDto(Long id, String nameDentist, String surnameDentist, LocalDateTime appointmentDateTime) {
        this.nameDentist = nameDentist;
        this.surnameDentist = surnameDentist;
        this.appointmentDateTime = appointmentDateTime;

    }

    public AppointmentDto() {
    }

//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }

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
