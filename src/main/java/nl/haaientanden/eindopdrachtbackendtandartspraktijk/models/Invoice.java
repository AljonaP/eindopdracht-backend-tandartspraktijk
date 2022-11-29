package nl.haaientanden.eindopdrachtbackendtandartspraktijk.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "invoices")
public class Invoice {

    @Id
    @GeneratedValue
    private Long id;
    private String nameDentalPractice = "Haaientanden B.V.";
    private String zipCodeDentalPractice = "1234AB";
    private Integer houseNumberDentalPractice = 22;
    private String phoneNumberDentalPractice = "01724654985";
    private String emailDentalPractice = "info@haaientanden.nl";
    private String ibanDentalPractice = "NL68ABNA0390075470";
    private String chamberOfCommerceNumber ="1235467";
    private LocalDate invoiceDate;
    private String invoiceNumber;
    private Double totalInvoiceAmount;
    private Double totalReimbursedByInsuranceCompanyAmount;
    private Double totalInvoiceAmountToPayByPatient;

    @OneToOne(cascade = CascadeType.ALL)
    Appointment appointment;

    @OneToMany(mappedBy = "invoice")
    @JsonIgnore
    private List<Treatment> treatments;

    public Invoice() {
    }
    public Invoice(Long id,
                   LocalDate invoiceDate,
                   String invoiceNumber) {
        this.id = id;
        this.nameDentalPractice = getNameDentalPractice();
        this.zipCodeDentalPractice = getZipCodeDentalPractice();
        this.houseNumberDentalPractice = getHouseNumberDentalPractice();
        this.phoneNumberDentalPractice = getPhoneNumberDentalPractice();
        this.emailDentalPractice = getEmailDentalPractice();
        this.ibanDentalPractice = getIbanDentalPractice();
        this.chamberOfCommerceNumber = getChamberOfCommerceNumber();
        this.invoiceDate = invoiceDate;
        this.invoiceNumber = invoiceNumber;
        this.appointment = appointment;
        this.totalInvoiceAmount = totalInvoiceAmount;
        this.totalReimbursedByInsuranceCompanyAmount = totalReimbursedByInsuranceCompanyAmount;
        this.totalInvoiceAmountToPayByPatient = totalInvoiceAmountToPayByPatient;

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

    public void setNameDentalPractice(String nameDentalPractice) {
        this.nameDentalPractice = nameDentalPractice;
    }

    public Appointment getAppointment() {
        return appointment;
    }

    public void setAppointment(Appointment appointment) {
        this.appointment = appointment;
    }

    public List<Treatment> getTreatments() {
        return treatments;
    }

    public void setTreatments(List<Treatment> treatments) {
        this.treatments = treatments;
    }
}
