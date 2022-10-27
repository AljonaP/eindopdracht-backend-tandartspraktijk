package nl.haaientanden.eindopdrachtbackendtandartspraktijk.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "invoices")
public class Invoice {
    @Id
    @GeneratedValue
    private Long id;
    private final String nameDentalPractice = "Haaientanden B.V.";
    private final String zipCodeDentalPractice = "1234AB";
    private final Integer houseNumberDentalPractice = 22;
    private final String phoneNumberDentalPractice = "0172 465 4985";
    private final String emailDentalPractice = "info@haaientanden.nl";
    private final String ibanDentalPractice = "NL68ABNA0390075470";
    private final String chamberOfCommerceNumber ="1235467";
    private LocalDate invoiceDate;
    private Long invoiceNumber;
    private String invoiceTextTemplate;
    private Double totalTreatmentAmount;
    private Double totalReimbursedByInsuranceCompanyAmount;
    private Double totalToPayByPatientPerTreatmentAmount;
    private Double totalInvoiceAmountToPayByPatient;

    public Invoice(Long id,
                   LocalDate invoiceDate,
                   Long invoiceNumber,
                   String invoiceTextTemplate,
                   Double totalTreatmentAmount,
                   Double totalReimbursedByInsuranceCompanyAmount,
                   Double totalToPayByPatientPerTreatmentAmount,
                   Double totalInvoiceAmountToPayByPatient) {
        this.id = id;
        this.invoiceNumber = invoiceNumber;
        this.invoiceTextTemplate = invoiceTextTemplate;
        this.totalTreatmentAmount = totalTreatmentAmount;
        this.totalReimbursedByInsuranceCompanyAmount = totalReimbursedByInsuranceCompanyAmount;
        this.totalToPayByPatientPerTreatmentAmount = totalToPayByPatientPerTreatmentAmount;
        this.totalInvoiceAmountToPayByPatient = totalInvoiceAmountToPayByPatient;
    }


    public Invoice() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameDentalPractice() {
        return nameDentalPractice;
    }

    public String getZipCodeDentalPractice() {
        return zipCodeDentalPractice;
    }

    public Integer getHouseNumberDentalPractice() {
        return houseNumberDentalPractice;
    }

    public String getPhoneNumberDentalPractice() {
        return phoneNumberDentalPractice;
    }

    public String getEmailDentalPractice() {
        return emailDentalPractice;
    }

    public String getIbanDentalPractice() {
        return ibanDentalPractice;
    }

    public String getChamberOfCommerceNumber() {
        return chamberOfCommerceNumber;
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