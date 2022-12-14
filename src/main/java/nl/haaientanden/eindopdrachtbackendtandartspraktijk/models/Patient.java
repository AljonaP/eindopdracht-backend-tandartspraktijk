package nl.haaientanden.eindopdrachtbackendtandartspraktijk.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

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
    @Transient
    private boolean setAppointment = true;
    @OneToMany(mappedBy = "patient")
    @JsonIgnore
    List<Appointment> appointments;

    public Patient(Long id,
                   String namePatient,
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
        this.id = id;
    }

    public boolean isSetAppointment() {
        return setAppointment;
    }

    public void setSetAppointment(boolean setAppointment) {
        this.setAppointment = setAppointment;
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

    public List<Appointment> getAppointments() {
        return appointments;
    }

    public void addAppointment(Appointment appointment) {
        appointments.add(appointment);
    }

    public void setAppointments(List<Appointment> appointments) {
        this.appointments = appointments;
    }
}
