package nl.haaientanden.eindopdrachtbackendtandartspraktijk.dtos;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDate;

public class InvoiceInputDto {
    @PastOrPresent
    private LocalDate invoiceDate;

    @NotNull
    private Long invoiceNumber;

    private Double totalTreatmentAmount;
    private Double totalReimbursedByInsuranceCompanyAmount;
    private Double totalToPayByPatientPerTreatmentAmount;
    private Double totalInvoiceAmountToPayByPatient;

    public InvoiceInputDto(LocalDate invoiceDate,
                           Long invoiceNumber,

                           Double totalTreatmentAmount,
                           Double totalReimbursedByInsuranceCompanyAmount,
                           Double totalToPayByPatientPerTreatmentAmount,
                           Double totalInvoiceAmountToPayByPatient) {
        this.invoiceDate = invoiceDate;
        this.invoiceNumber = invoiceNumber;

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
