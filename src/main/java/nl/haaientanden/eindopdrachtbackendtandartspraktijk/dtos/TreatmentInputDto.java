package nl.haaientanden.eindopdrachtbackendtandartspraktijk.dtos;

import javax.validation.constraints.*;

public class TreatmentInputDto {
    @NotBlank(message = "Veld verplicht invullen")
    @Size(min=3, max=3)
    private String treatmentCode;

    @NotBlank(message = "Veld verplicht invullen")
    @Size(min=3, max=3)
    private String treatmentfDescription;

    @NotEmpty(message = "Veld verplicht invullen")
    @Positive
    private Double treatmentRate;

    public TreatmentInputDto(String treatmentCode, String treatmentfDescription, Double treatmentRate) {
        this.treatmentCode = treatmentCode;
        this.treatmentfDescription = treatmentfDescription;
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
