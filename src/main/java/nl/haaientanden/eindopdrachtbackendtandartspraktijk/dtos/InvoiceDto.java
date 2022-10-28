package nl.haaientanden.eindopdrachtbackendtandartspraktijk.dtos;

import java.time.LocalDate;

public class InvoiceDto {
    private Long id;

    private LocalDate invoiceDate;
    private Long invoiceNumber;

    public InvoiceDto(Long id,
                   LocalDate invoiceDate,
                   Long invoiceNumber
                   ) {
        this.id = id;
        this.invoiceNumber = invoiceNumber;

    }
    public InvoiceDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
