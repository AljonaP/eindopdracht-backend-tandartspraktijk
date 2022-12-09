package nl.haaientanden.eindopdrachtbackendtandartspraktijk.dtos;

import javax.validation.constraints.*;
import java.time.LocalDateTime;

public class AppointmentInputDto {

    @NotBlank(message = "Veld verplicht invullen")
    @Size(min=1, max=30)
    private String nameDentist;
    @NotBlank(message = "Veld verplicht invullen")
    @Size(min=1, max=30)
    private String surnameDentist;
   @NotNull
    private LocalDateTime appointmentDateTime;

    public AppointmentInputDto(String nameDentist,
                               String surnameDentist,
                               LocalDateTime appointmentDateTime) {
        this.nameDentist = nameDentist;
        this.surnameDentist = surnameDentist;
        this.appointmentDateTime = appointmentDateTime;
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

    public LocalDateTime getAppointmentDateTime() {
        return appointmentDateTime;
    }

    public void setAppointmentDateTime(LocalDateTime appointmentDateTime) {
        this.appointmentDateTime = appointmentDateTime;
    }
}
