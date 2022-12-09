package nl.haaientanden.eindopdrachtbackendtandartspraktijk.dtos;

import javax.validation.constraints.*;
import java.time.LocalDate;

public class PatientInputDto {
    @NotBlank(message = "Veld verplicht invullen")
    @Size(min=1, max=30)
    private String namePatient;
    @NotBlank(message = "Veld verplicht invullen")
    @Size(min=1, max=30)
    private String surnamePatient;
    @Past
    private LocalDate dob;
    @NotBlank
    @Size(min=6, max=6)
    private String zipCode;
    @NotNull
    private Integer homeNumber;
    @Email
    private String email;
    @NotNull
    @Size(min = 10, max = 10)
    private String phoneNumber;
    @NotNull
    private Integer reimburseByInsurancePercentage;

    public PatientInputDto(String namePatient,
                           String surnamePatient,
                           LocalDate dob,
                           String zipCode,
                           Integer homeNumber,
                           String email,
                           String phoneNumber,
                           Integer reimburseByInsurancePercentage) {
        this.namePatient = namePatient;
        this.surnamePatient = surnamePatient;
        this.dob = dob;
        this.zipCode = zipCode;
        this.homeNumber = homeNumber;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.reimburseByInsurancePercentage = reimburseByInsurancePercentage;
    }

    public PatientInputDto() {
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
