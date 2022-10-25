package nl.haaientanden.eindopdrachtbackendtandartspraktijk.models;

import org.springframework.http.converter.json.GsonBuilderUtils;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "patients")
public class Patient {
    @Id
    @GeneratedValue
    private Long id;
    private String namePatient;
    private String surnamePatient;
    private LocalDate dob;
    private String zipCode;
    private Integer homeNumber;
    private String email;
    private String phoneNumber;
    private Integer reimburseByInsurancePercentage;

    public Patient(Long id, String namePatient, String surnamePatient, LocalDate dob, String zipCode, Integer homeNumber, String email, String phoneNumber, Integer reimburseByInsurancePercentage) {
        this.namePatient = namePatient;
        this.surnamePatient = surnamePatient;
        this.dob = dob;
        this.zipCode = zipCode;
        this.homeNumber = homeNumber;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.reimburseByInsurancePercentage = reimburseByInsurancePercentage;
        this.id = id;
    }

    public Patient() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNamePatient() {
        return namePatient;
    }

    public void setNamePatient(String namePatient) {
        this.namePatient = namePatient;
    }

    public String getSurnamePatient() {
        return surnamePatient;
    }

    public void setSurnamePatient(String surnamePatient) {
        this.surnamePatient = surnamePatient;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public Integer getHomeNumber() {
        return homeNumber;
    }

    public void setHomeNumber(Integer homeNumber) {
        this.homeNumber = homeNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Integer getReimburseByInsurancePercentage() {
        return reimburseByInsurancePercentage;
    }

    public void setReimburseByInsurancePercentage(Integer reimburseByInsurancePercentage) {
        this.reimburseByInsurancePercentage = reimburseByInsurancePercentage;
    }
}
