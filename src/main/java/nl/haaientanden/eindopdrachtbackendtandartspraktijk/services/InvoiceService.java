package nl.haaientanden.eindopdrachtbackendtandartspraktijk.services;

import nl.haaientanden.eindopdrachtbackendtandartspraktijk.dtos.InvoiceDto;
import nl.haaientanden.eindopdrachtbackendtandartspraktijk.dtos.InvoiceInputDto;
import nl.haaientanden.eindopdrachtbackendtandartspraktijk.exceptions.RecordNotFoundException;
import nl.haaientanden.eindopdrachtbackendtandartspraktijk.models.Invoice;
import nl.haaientanden.eindopdrachtbackendtandartspraktijk.repositories.InvoiceRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    public List<InvoiceDto> getInvoices() {

        List<Invoice> invoices = invoiceRepository.findAll();
        List<InvoiceDto> dtos = new ArrayList<>();
        for (Invoice invoice : invoices) {
            dtos.add(transferToDto(invoice));
        }
        return dtos;
    }

    public InvoiceDto getInvoiceById(Long id) {

        if(invoiceRepository.findById(id).isPresent()){
            Invoice invoice = invoiceRepository.findById(id).get();
            return transferToDto(invoice);
        } else {
            throw new RecordNotFoundException("The entered value isn't correct or doesn't exist. Search again with another value.");
        }
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
