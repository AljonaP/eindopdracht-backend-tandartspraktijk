package nl.haaientanden.eindopdrachtbackendtandartspraktijk.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="treatments")
public class Treatment {
    @Id
    @GeneratedValue
    private String treatmentCode;
    private String treatmentfDescription;
    private Double treatmentRate;

    public Treatment(String treatmentCode, String treatmentfDescription, Double treatmentRate) {
        this.treatmentCode = treatmentCode;
        this.treatmentfDescription = treatmentfDescription;
        this.treatmentRate = treatmentRate;
    }

    public Treatment() {
    }

    public String getTreatmentCode() {
        return treatmentCode;
    }

    public void setTreatmentCode(String treatmentCode) {
        this.treatmentCode = treatmentCode;
    }

    public String getTreatmentfDescription() {
        return treatmentfDescription;
    }

    public void setTreatmentfDescription(String treatmentfDescription) {
        this.treatmentfDescription = treatmentfDescription;
    }

    public Double getTreatmentRate() {
        return treatmentRate;
    }

    public void setTreatmentRate(Double treatmentRate) {
        this.treatmentRate = treatmentRate;
    }
}

