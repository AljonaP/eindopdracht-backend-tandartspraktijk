package nl.haaientanden.eindopdrachtbackendtandartspraktijk.dtos;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import nl.haaientanden.eindopdrachtbackendtandartspraktijk.models.Invoice;

public class TreatmentDto {
    private Long id;
    private String treatmentCode;
    private String treatmentDescription;
    private Double treatmentRate;

    @JsonSerialize
    private Invoice invoice;

    public TreatmentDto(Long id,
                        String treatmentCode,
                        String treatmentDescription,
                        Double treatmentRate) {

        this.id = id;
        this.treatmentCode = treatmentCode;
        this.treatmentDescription = treatmentDescription;
        this.treatmentRate = treatmentRate;
    }

    public TreatmentDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }
}
