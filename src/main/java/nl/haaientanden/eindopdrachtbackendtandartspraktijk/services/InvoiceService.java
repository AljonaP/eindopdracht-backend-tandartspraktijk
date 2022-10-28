package nl.haaientanden.eindopdrachtbackendtandartspraktijk.services;

import nl.haaientanden.eindopdrachtbackendtandartspraktijk.dtos.InvoiceDto;
import nl.haaientanden.eindopdrachtbackendtandartspraktijk.dtos.InvoiceInputDto;
import nl.haaientanden.eindopdrachtbackendtandartspraktijk.models.Invoice;
import nl.haaientanden.eindopdrachtbackendtandartspraktijk.repositories.InvoiceRepository;
import org.springframework.stereotype.Service;

@Service
public class InvoiceService {
    private final InvoiceRepository invoiceRepository;

    public InvoiceService(InvoiceRepository invoiceRepository) {
        this.invoiceRepository = invoiceRepository;
    }

    public InvoiceDto saveInvoice(InvoiceInputDto dto) {

        Invoice invoice = transferToInvoice(dto);
        invoiceRepository.save(invoice);

        return transferToDto(invoice);
    }


    public static Invoice transferToInvoice(InvoiceInputDto dto) {

        var invoice = new Invoice();

        invoice.setInvoiceDate(dto.getInvoiceDate());
        invoice.setInvoiceNumber(dto.getInvoiceNumber());

        return invoice;
    }

    public static InvoiceDto transferToDto(Invoice invoice) {

        InvoiceDto dto = new InvoiceDto();

        dto.setId(invoice.getId());
        dto.setInvoiceDate(invoice.getInvoiceDate());
        dto.setInvoiceNumber(invoice.getInvoiceNumber());

        return dto;
    }
}
