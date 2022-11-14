package nl.haaientanden.eindopdrachtbackendtandartspraktijk.controllers;

import nl.haaientanden.eindopdrachtbackendtandartspraktijk.dtos.AppointmentDto;
import nl.haaientanden.eindopdrachtbackendtandartspraktijk.dtos.InvoiceDto;
import nl.haaientanden.eindopdrachtbackendtandartspraktijk.dtos.InvoiceInputDto;
import nl.haaientanden.eindopdrachtbackendtandartspraktijk.repositories.InvoiceRepository;
import nl.haaientanden.eindopdrachtbackendtandartspraktijk.services.InvoiceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.List;

import static nl.haaientanden.eindopdrachtbackendtandartspraktijk.utils.UtilityMethodes.getErrorMessage;

@RestController
@RequestMapping("/haaientanden/facturen")
public class InvoiceController {
    private final InvoiceRepository invoiceRepository;
    private final InvoiceService invoiceService;

    public InvoiceController(InvoiceRepository invoiceRepository, InvoiceService invoiceService) {
        this.invoiceRepository = invoiceRepository;
        this.invoiceService = invoiceService;
    }

    @PostMapping("")
    public ResponseEntity<Object> addInvoice(@Valid @RequestBody InvoiceInputDto invoiceInputDto, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return new ResponseEntity<>(getErrorMessage(bindingResult), HttpStatus.BAD_REQUEST);
        }
        InvoiceDto dto = invoiceService.saveInvoice(invoiceInputDto);
        return ResponseEntity.created(null).body(dto);
    }

    @GetMapping("")
    public ResponseEntity<List<InvoiceDto>> getAllInvoices() {
        return ResponseEntity.ok().body(invoiceService.getInvoices());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getInvoice(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(invoiceService.getInvoiceById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateInvoice(@PathVariable(name="id") Long id, @RequestBody InvoiceInputDto newInvoice) {
        InvoiceDto dto = invoiceService.updateInvoice(id, newInvoice);

        return ResponseEntity.ok().body(dto);
    }

    @DeleteMapping("/{id}")
    public void deleteInvoice(@PathVariable(name = "id") Long id) {
        invoiceService.deleteInvoiceById(id);
    }
}
