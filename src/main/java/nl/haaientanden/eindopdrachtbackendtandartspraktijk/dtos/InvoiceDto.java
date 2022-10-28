package nl.haaientanden.eindopdrachtbackendtandartspraktijk.dtos;

import java.time.LocalDate;

public class InvoiceDto {
    private Long id;

    private LocalDate invoiceDate;
    private String invoiceNumber;

    public InvoiceDto(Long id,
                   LocalDate invoiceDate,
                   String invoiceNumber
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

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }
}
