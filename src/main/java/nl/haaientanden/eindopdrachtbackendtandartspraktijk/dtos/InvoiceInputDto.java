package nl.haaientanden.eindopdrachtbackendtandartspraktijk.dtos;

import javax.validation.constraints.*;
import java.time.LocalDate;

public class InvoiceInputDto {
    @PastOrPresent
    private LocalDate invoiceDate;

    @NotBlank
    @Size(min = 7, max = 7)
    private String invoiceNumber;
    @NotNull
    private Long appointmentId;

    public InvoiceInputDto(LocalDate invoiceDate,
                           String invoiceNumber) {
        this.invoiceDate = invoiceDate;
        this.invoiceNumber = invoiceNumber;

    }

    public LocalDate getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(LocalDate invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public Long getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(Long appointmentId) {
        this.appointmentId = appointmentId;
    }
}
