package nl.haaientanden.eindopdrachtbackendtandartspraktijk.models;

import javax.persistence.*;

@Entity
@Table(name="treatments")
public class Treatment {
    @Id
    private String treatmentCode;
    private String treatmentDescription;
    private Double treatmentRate;
    public Treatment(String treatmentCode, String treatmentDescription, Double treatmentRate) {
        this.treatmentCode = treatmentCode;
        this.treatmentDescription = treatmentDescription;
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

    public String getTreatmentDescription() {
        return treatmentDescription;
    }

    public void setTreatmentDescription(String treatmentDescription) {
        this.treatmentDescription = treatmentDescription;
    }

    public Double getTreatmentRate() {
        return treatmentRate;
    }

    public void setTreatmentRate(Double treatmentRate) {
        this.treatmentRate = treatmentRate;
    }
}

