package nl.haaientanden.eindopdrachtbackendtandartspraktijk.dtos;

public class TreatmentDto {
    private String treatmentCode;
    private String treatmentfDescription;
    private Double treatmentRate;

    public TreatmentDto(String treatmentCode, String treatmentfDescription, Double treatmentRate) {
        this.treatmentCode = treatmentCode;
        this.treatmentfDescription = treatmentfDescription;
        this.treatmentRate = treatmentRate;
    }

    public TreatmentDto() {
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
