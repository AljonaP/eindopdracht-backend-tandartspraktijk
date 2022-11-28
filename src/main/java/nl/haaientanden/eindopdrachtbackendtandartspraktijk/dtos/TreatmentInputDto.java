package nl.haaientanden.eindopdrachtbackendtandartspraktijk.dtos;

import javax.validation.constraints.*;

public class TreatmentInputDto {

    @NotBlank(message = "Veld verplicht invullen")
    @Size(min=3, max=3)
    private String treatmentCode;

    @NotBlank(message = "Veld verplicht invullen")
    @Size(min=3, max=500)
    private String treatmentDescription;

    @Positive
    private Double treatmentRate;

    public TreatmentInputDto(String treatmentCode,
                             String treatmentDescription,
                             Double treatmentRate) {

        this.treatmentCode = treatmentCode;
        this.treatmentDescription = treatmentDescription;
        this.treatmentRate = treatmentRate;
    }

    public TreatmentInputDto() {
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
