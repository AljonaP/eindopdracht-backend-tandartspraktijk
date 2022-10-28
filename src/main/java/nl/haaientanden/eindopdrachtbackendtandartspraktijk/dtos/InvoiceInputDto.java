package nl.haaientanden.eindopdrachtbackendtandartspraktijk.dtos;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;
import java.time.LocalDate;

public class InvoiceInputDto {
    @PastOrPresent
    private LocalDate invoiceDate;

    @NotBlank
    @Size(min = 7, max = 7)
    private String invoiceNumber;


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
}
