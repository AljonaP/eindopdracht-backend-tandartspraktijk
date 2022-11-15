package nl.haaientanden.eindopdrachtbackendtandartspraktijk.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import nl.haaientanden.eindopdrachtbackendtandartspraktijk.models.Appointment;
import nl.haaientanden.eindopdrachtbackendtandartspraktijk.models.AppointmentTreatment;
import nl.haaientanden.eindopdrachtbackendtandartspraktijk.models.Treatment;

import java.time.LocalDate;
import java.util.List;

public class InvoiceDto {
    private Long id;

    private LocalDate invoiceDate;
    private String invoiceNumber;
    private Double totalInvoiceAmount;
    private Double totalReimbursedByInsuranceCompanyAmount;
    private Double totalInvoiceAmountToPayByPatient;

    @JsonIgnore
    private AppointmentDto appointmentDto;
//    private AppointmentTreatment appointmentTreatment;

    @JsonIgnore
    private List<TreatmentDto> treatmentDtos;
    public InvoiceDto(Long id,
                      LocalDate invoiceDate,
                      String invoiceNumber,
                      Double totalInvoiceAmount,
                      Double totalReimbursedByInsuranceCompanyAmount,
                      Double totalInvoiceAmountToPayByPatient,
                      AppointmentDto appointmentDto
//                      AppointmentTreatment appointmentTreatment
    ) {
        this.id = id;
        this.invoiceNumber = invoiceNumber;
        this.invoiceDate = invoiceDate;
        this.totalInvoiceAmount = totalInvoiceAmount;
        this.totalReimbursedByInsuranceCompanyAmount = totalReimbursedByInsuranceCompanyAmount;
        this.totalInvoiceAmountToPayByPatient = totalInvoiceAmountToPayByPatient;

        this.appointmentDto = appointmentDto;
    }

    public InvoiceDto(Long id,
                   LocalDate invoiceDate,
                   String invoiceNumber,
                   Double totalInvoiceAmount,
                   Double totalReimbursedByInsuranceCompanyAmount,
                   Double totalInvoiceAmountToPayByPatient,
                   AppointmentDto appointmentDto,
                   List<TreatmentDto> treatmentDtos) {
        this.id = id;
        this.invoiceNumber = invoiceNumber;
        this.invoiceDate = invoiceDate;
        this.totalInvoiceAmount = totalInvoiceAmount;
        this.totalReimbursedByInsuranceCompanyAmount = totalReimbursedByInsuranceCompanyAmount;
        this.totalInvoiceAmountToPayByPatient = totalInvoiceAmountToPayByPatient;
        this.appointmentDto = appointmentDto;
        this.treatmentDtos =treatmentDtos;
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

    public Double getTotalInvoiceAmount() {
        return totalInvoiceAmount;
    }

    public void setTotalInvoiceAmount(Double totalInvoiceAmount) {
        this.totalInvoiceAmount = totalInvoiceAmount;
    }

    public Double getTotalReimbursedByInsuranceCompanyAmount() {
        return totalReimbursedByInsuranceCompanyAmount;
    }

    public void setTotalReimbursedByInsuranceCompanyAmount(Double totalReimbursedByInsuranceCompanyAmount) {
        this.totalReimbursedByInsuranceCompanyAmount = totalReimbursedByInsuranceCompanyAmount;
    }

    public Double getTotalInvoiceAmountToPayByPatient() {
        return totalInvoiceAmountToPayByPatient;
    }

    public void setTotalInvoiceAmountToPayByPatient(Double totalInvoiceAmountToPayByPatient) {
        this.totalInvoiceAmountToPayByPatient = totalInvoiceAmountToPayByPatient;
    }

    public List<TreatmentDto> getTreatmentDtos() {
        return treatmentDtos;
    }

    public void setTreatmentDtos(List<TreatmentDto> treatmentDtos) {
        this.treatmentDtos = treatmentDtos;
    }

    public AppointmentDto getAppointmentDto() {
        return appointmentDto;
    }

    public void setAppointmentDto(AppointmentDto appointmentDto) {
        this.appointmentDto = appointmentDto;
    }

//    public AppointmentTreatment getAppointmentTreatment() {
//        return appointmentTreatment;
//    }
//
//    public void setAppointmentTreatment(AppointmentTreatment appointmentTreatment) {
//        this.appointmentTreatment = appointmentTreatment;
//    }
}
