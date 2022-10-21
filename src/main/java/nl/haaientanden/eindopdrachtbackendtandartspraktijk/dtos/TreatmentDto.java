package nl.haaientanden.eindopdrachtbackendtandartspraktijk.dtos;

public class TreatmentDto {
    private String treatmentCode;
    private String treatmentDescription;
    private Double treatmentRate;

    public TreatmentDto(String treatmentCode, String treatmentDescription, Double treatmentRate) {
        this.treatmentCode = treatmentCode;
        this.treatmentDescription = treatmentDescription;
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

    public String getTreatmentDescription() {
        return treatmentDescription;
    }

    public void setTreatmentfDescription(String treatmentDescription) {
        this.treatmentDescription = treatmentDescription;
    }

    public Double getTreatmentRate() {
        return treatmentRate;
    }

    public void setTreatmentRate(Double treatmentRate) {
        this.treatmentRate = treatmentRate;
    }
}
