package nl.haaientanden.eindopdrachtbackendtandartspraktijk.dtos;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.time.LocalDateTime;

public class AppointmentDto {
    private Long id;
    private String nameDentist;
    private String surnameDentist;
    private LocalDateTime appointmentDateTime;

    @JsonSerialize
    private TreatmentRoomDto treatmentRoomDto;

    public AppointmentDto(Long id, String nameDentist, String surnameDentist, LocalDateTime appointmentDateTime) {
        this.nameDentist = nameDentist;
        this.surnameDentist = surnameDentist;
        this.appointmentDateTime = appointmentDateTime;

    }

    public AppointmentDto(Long id, String nameDentist, String surnameDentist, LocalDateTime appointmentDateTime, TreatmentRoomDto treatmentRoomDto) {
        this.id = id;
        this.nameDentist = nameDentist;
        this.surnameDentist = surnameDentist;
        this.appointmentDateTime = appointmentDateTime;
        this.treatmentRoomDto = treatmentRoomDto;
    }

    public AppointmentDto() {
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

    public TreatmentRoomDto getTreatmentRoomDto() {
        return treatmentRoomDto;
    }

    public void setTreatmentRoomDto(TreatmentRoomDto treatmentRoomDto) {
        this.treatmentRoomDto = treatmentRoomDto;
    }
}
