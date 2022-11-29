package nl.haaientanden.eindopdrachtbackendtandartspraktijk.services;

import nl.haaientanden.eindopdrachtbackendtandartspraktijk.dtos.AppointmentDto;
import nl.haaientanden.eindopdrachtbackendtandartspraktijk.dtos.InvoiceDto;
import nl.haaientanden.eindopdrachtbackendtandartspraktijk.dtos.InvoiceInputDto;
import nl.haaientanden.eindopdrachtbackendtandartspraktijk.dtos.TreatmentDto;
import nl.haaientanden.eindopdrachtbackendtandartspraktijk.exceptions.RecordNotFoundException;
import nl.haaientanden.eindopdrachtbackendtandartspraktijk.models.*;
import nl.haaientanden.eindopdrachtbackendtandartspraktijk.repositories.AppointmentRepository;
import nl.haaientanden.eindopdrachtbackendtandartspraktijk.repositories.AppointmentTreatmentRepository;
import nl.haaientanden.eindopdrachtbackendtandartspraktijk.repositories.InvoiceRepository;

import java.util.*;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class InvoiceService {

    private final InvoiceRepository invoiceRepository;
    private final AppointmentRepository appointmentRepository;
    private final AppointmentService appointmentService;
    private final AppointmentTreatmentRepository appointmentTreatmentRepository;
    private final AppointmentTreatmentService appointmentTreatmentService;

    public InvoiceService(InvoiceRepository invoiceRepository,
                          AppointmentRepository appointmentRepository,
                          AppointmentService appointmentService,
                          AppointmentTreatmentRepository appointmentTreatmentRepository,
                          AppointmentTreatmentService appointmentTreatmentService) {

        this.invoiceRepository = invoiceRepository;
        this.appointmentRepository = appointmentRepository;
        this.appointmentService = appointmentService;
        this.appointmentTreatmentRepository = appointmentTreatmentRepository;
        this.appointmentTreatmentService = appointmentTreatmentService;
    }

    public InvoiceDto saveInvoice(InvoiceInputDto dto) {

        Invoice invoice = transferToInvoice(dto);
        Optional<Appointment> appointmentOptional = appointmentRepository.findById(dto.getAppointmentId());
        if (appointmentOptional.isPresent()) {
            Appointment appointment = appointmentOptional.get();
            invoice.setAppointment(appointment);

            Collection<AppointmentTreatment> appointmentTreatmentCollection = appointment.getAppointmentTreatment();
            List<Treatment> treatmentList = new ArrayList<>();
            Double totalAmount = 0.0;
            for (AppointmentTreatment appointmentTreatment : appointmentTreatmentCollection) {
                Treatment treatment = appointmentTreatment.getTreatment();

                treatmentList.add(treatment);
                totalAmount += treatment.getTreatmentRate();
                invoice.setTreatments(treatmentList);
            }
            invoice.setTotalInvoiceAmount(Math.round(totalAmount * 100) / 100.0);

            Patient patient = appointment.getPatient();
            Integer reimburseByInsurancePercentage = patient.getReimburseByInsurancePercentage();
            Double totalReimbursedByInsuranceCompanyAmount = ((totalAmount) / 100) * reimburseByInsurancePercentage;
            invoice.setTotalReimbursedByInsuranceCompanyAmount(Math.round(totalReimbursedByInsuranceCompanyAmount * 100) / 100.0);

            Double totalInvoiceAmountToPayByPatient = (totalAmount - totalReimbursedByInsuranceCompanyAmount);
            invoice.setTotalInvoiceAmountToPayByPatient(Math.round(totalInvoiceAmountToPayByPatient * 100) / 100.0);
        } else {
            throw new RecordNotFoundException("Appointment Id number isn't found.");
        }
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

        if (invoiceRepository.findById(id).isPresent()) {
            Invoice invoice = invoiceRepository.findById(id).get();

            return transferToDto(invoice);
        } else {
            throw new RecordNotFoundException("The entered value isn't correct or doesn't exist. Search again with another value.");
        }
    }

    public InvoiceDto updateInvoice(Long id, InvoiceInputDto inputDto) {

        if (invoiceRepository.findById(id).isPresent()) {
            Invoice invoice = invoiceRepository.findById(id).get();
            Invoice invoice1 = transferToInvoice(inputDto);

            Optional<Appointment> appointmentOptional = appointmentRepository.findById(inputDto.getAppointmentId());
            if (appointmentOptional.isPresent()) {
                Appointment appointment = appointmentOptional.get();
                invoice1.setAppointment(appointment);

                Collection<AppointmentTreatment> appointmentTreatmentCollection = appointment.getAppointmentTreatment();
                List<Treatment> treatmentList = new ArrayList<>();
                Double totalAmount = 0.0;
                for (AppointmentTreatment appointmentTreatment : appointmentTreatmentCollection) {
                    Treatment treatment = appointmentTreatment.getTreatment();

                    treatmentList.add(treatment);
                    totalAmount += treatment.getTreatmentRate();
                    invoice1.setTreatments(treatmentList);
                }
                invoice1.setTotalInvoiceAmount(Math.round(totalAmount * 100) / 100.0);

                Patient patient = appointment.getPatient();
                Integer reimburseByInsurancePercentage = patient.getReimburseByInsurancePercentage();
                Double totalReimbursedByInsuranceCompanyAmount = ((totalAmount) / 100) * reimburseByInsurancePercentage;
                invoice1.setTotalReimbursedByInsuranceCompanyAmount(Math.round(totalReimbursedByInsuranceCompanyAmount * 100) / 100.0);

                Double totalInvoiceAmountToPayByPatient = (totalAmount - totalReimbursedByInsuranceCompanyAmount);
                invoice1.setTotalInvoiceAmountToPayByPatient(Math.round(totalInvoiceAmountToPayByPatient * 100) / 100.0);
            } else {
                throw new RecordNotFoundException("Appointment isn't found.");
            }

            invoice1.setId(invoice.getId());

            invoiceRepository.save(invoice1);

            return transferToDto(invoice1);
        } else {
            throw new RecordNotFoundException("No connected treatment(s) to the appointment. Connect treatments to the appointment and try to save invoice again with chosen appointment.");
        }
    }

    public void deleteInvoiceById(@RequestBody Long id) {

        invoiceRepository.deleteById(id);
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
        if (!(invoice.getTreatments() == null)) {
            List<Treatment> treatments = invoice.getTreatments();
            List<TreatmentDto> treatmentDtoList = TreatmentService.transferTreatmentListToDtoList(treatments);
            dto.setTreatmentDtos(treatmentDtoList);
        }
        if (!(invoice.getAppointment() == null)) {
            Appointment appointment = invoice.getAppointment();
            AppointmentDto appointmentDto = AppointmentService.transferToDto(appointment);
            dto.setAppointmentDto(appointmentDto);
        }
        dto.setTotalInvoiceAmount(invoice.getTotalInvoiceAmount());
        dto.setTotalReimbursedByInsuranceCompanyAmount(invoice.getTotalReimbursedByInsuranceCompanyAmount());
        dto.setTotalInvoiceAmountToPayByPatient(invoice.getTotalInvoiceAmountToPayByPatient());

        return dto;
    }
}
