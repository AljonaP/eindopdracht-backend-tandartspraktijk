package nl.haaientanden.eindopdrachtbackendtandartspraktijk.dtos;

import java.time.LocalDate;

public class InvoiceInputDto {
    private LocalDate invoiceDate;
    private Long invoiceNumber;
    private String invoiceTextTemplate;
    private Double totalTreatmentAmount;
    private Double totalReimbursedByInsuranceCompanyAmount;
    private Double totalToPayByPatientPerTreatmentAmount;
    private Double totalInvoiceAmountToPayByPatient;

    public InvoiceInputDto(LocalDate invoiceDate,
                           Long invoiceNumber,
                           String invoiceTextTemplate,
                           Double totalTreatmentAmount,
                           Double totalReimbursedByInsuranceCompanyAmount,
                           Double totalToPayByPatientPerTreatmentAmount,
                           Double totalInvoiceAmountToPayByPatient) {
        this.invoiceDate = invoiceDate;
        this.invoiceNumber = invoiceNumber;
        this.invoiceTextTemplate = invoiceTextTemplate;
        this.totalTreatmentAmount = totalTreatmentAmount;
        this.totalReimbursedByInsuranceCompanyAmount = totalReimbursedByInsuranceCompanyAmount;
        this.totalToPayByPatientPerTreatmentAmount = totalToPayByPatientPerTreatmentAmount;
        this.totalInvoiceAmountToPayByPatient = totalInvoiceAmountToPayByPatient;
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

    public String getInvoiceTextTemplate() {
        return invoiceTextTemplate;
    }

    public void setInvoiceTextTemplate(String invoiceTextTemplate) {
        this.invoiceTextTemplate = invoiceTextTemplate;
    }

    public Double getTotalTreatmentAmount() {
        return totalTreatmentAmount;
    }

    public void setTotalTreatmentAmount(Double totalTreatmentAmount) {
        this.totalTreatmentAmount = totalTreatmentAmount;
    }

    public Double getTotalReimbursedByInsuranceCompanyAmount() {
        return totalReimbursedByInsuranceCompanyAmount;
    }

    public void setTotalReimbursedByInsuranceCompanyAmount(Double totalReimbursedByInsuranceCompanyAmount) {
        this.totalReimbursedByInsuranceCompanyAmount = totalReimbursedByInsuranceCompanyAmount;
    }

    public Double getTotalToPayByPatientPerTreatmentAmount() {
        return totalToPayByPatientPerTreatmentAmount;
    }

    public void setTotalToPayByPatientPerTreatmentAmount(Double totalToPayByPatientPerTreatmentAmount) {
        this.totalToPayByPatientPerTreatmentAmount = totalToPayByPatientPerTreatmentAmount;
    }

    public Double getTotalInvoiceAmountToPayByPatient() {
        return totalInvoiceAmountToPayByPatient;
    }

    public void setTotalInvoiceAmountToPayByPatient(Double totalInvoiceAmountToPayByPatient) {
        this.totalInvoiceAmountToPayByPatient = totalInvoiceAmountToPayByPatient;
    }
}
