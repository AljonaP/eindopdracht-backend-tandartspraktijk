package nl.haaientanden.eindopdrachtbackendtandartspraktijk.dtos;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.sql.Time;
import java.time.LocalDate;

public class AppointmentInputDto {
    @NotBlank(message = "Veld verplicht invullen")
    @Size(min=1, max=30)
    private String nameDentist;

    @NotBlank(message = "Veld verplicht invullen")
    @Size(min=1, max=30)
    private String surnameDentist;

    @NotBlank
    private LocalDate appointmentDate;

    @NotBlank
    private Time appointmentTime;

    public AppointmentInputDto(String nameDentist, String surnameDentist, LocalDate appointmentDate, Time appointmentTime) {
        this.nameDentist = nameDentist;
        this.surnameDentist = surnameDentist;
        this.appointmentDate = appointmentDate;
        this.appointmentTime = appointmentTime;
    }

    public AppointmentInputDto() {
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
