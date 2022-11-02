package nl.haaientanden.eindopdrachtbackendtandartspraktijk.models;


import javax.persistence.*;

@Entity
public class AppointmentTreatment {
    @EmbeddedId
    private AppointmentTreatmentKey id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("appointmentId")
    @JoinColumn(name = "appointment_id")
    private Appointment appointment;

    @ManyToOne
    @MapsId("treatmentId")
    @JoinColumn(name = "treatment_id")
    private Treatment treatment;

    public AppointmentTreatmentKey getId() {
        return id;
    }

    public void setId(AppointmentTreatmentKey id) {
        this.id = id;
    }

    public Appointment getAppointment() {
        return appointment;
    }

    public void setAppointment(Appointment appointment) {
        this.appointment = appointment;
    }

    public Treatment getTreatment() {
        return treatment;
    }

    public void setTreatment(Treatment treatment) {
        this.treatment = treatment;
    }
}
