package nl.haaientanden.eindopdrachtbackendtandartspraktijk.dtos;

import java.time.LocalDate;
import java.util.List;

public class InvoiceDto {

    private Long id;
    private LocalDate invoiceDate;
    private String invoiceNumber;
    private Double totalInvoiceAmount;
    private Double totalReimbursedByInsuranceCompanyAmount;
    private Double totalInvoiceAmountToPayByPatient;
    private AppointmentDto appointmentDto;
    private List<TreatmentDto> treatmentDtos;

    public InvoiceDto(Long id,
                      LocalDate invoiceDate,
                      String invoiceNumber,
                      Double totalInvoiceAmount,
                      Double totalReimbursedByInsuranceCompanyAmount,
                      Double totalInvoiceAmountToPayByPatient,
                      AppointmentDto appointmentDto) {

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
}
