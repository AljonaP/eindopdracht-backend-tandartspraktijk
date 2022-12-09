package nl.haaientanden.eindopdrachtbackendtandartspraktijk.models;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class AppointmentTreatmentKey implements Serializable {
    private Long appointmentId;
    private Long treatmentId;

    public AppointmentTreatmentKey() {
    }

    public AppointmentTreatmentKey(Long appointmentId, Long treatmentId) {
        this.appointmentId = appointmentId;
        this.treatmentId = treatmentId;
    }

    public Long getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(Long appointmentId) {
        this.appointmentId = appointmentId;
    }

    public Long getTreatmentId() {
        return treatmentId;
    }

    public void setTreatmentId(Long treatmentId) {
        this.treatmentId = treatmentId;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        AppointmentTreatmentKey that = (AppointmentTreatmentKey) object;

        return appointmentId.equals(that.appointmentId) && treatmentId.equals(that.treatmentId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(appointmentId, treatmentId);
    }
}



