package nl.haaientanden.eindopdrachtbackendtandartspraktijk.models;


import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
public class AppointmentTreatment {
    @EmbeddedId
    private AppointmentTreatmentKey id;
}
