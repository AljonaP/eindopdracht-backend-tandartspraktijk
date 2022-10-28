package nl.haaientanden.eindopdrachtbackendtandartspraktijk.dtos;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDate;

public class InvoiceInputDto {
    @PastOrPresent
    private LocalDate invoiceDate;

    @NotNull
    private Long invoiceNumber;


    public InvoiceInputDto(LocalDate invoiceDate,
                           Long invoiceNumber) {
        this.invoiceDate = invoiceDate;
        this.invoiceNumber = invoiceNumber;
    }

    public LocalDate getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(LocalDate invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public Long getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(Long invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }
}
