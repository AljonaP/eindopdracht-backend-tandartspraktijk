package nl.haaientanden.eindopdrachtbackendtandartspraktijk.models;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "treatments")
public class Treatment {

    @Id
    @GeneratedValue
    private Long id;
    private String treatmentCode;
    private String treatmentDescription;
    private Double treatmentRate;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "invoice_id")
    private Invoice invoice;

    public Treatment(Long id,
                     String treatmentCode,
                     String treatmentDescription,
                     Double treatmentRate) {
        this.id = id;
        this.treatmentCode = treatmentCode;
        this.treatmentDescription = treatmentDescription;
        this.treatmentRate = treatmentRate;
    }

    public Treatment() {
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

    public void setTreatmentDescription(String treatmentDescription) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Treatment treatment = (Treatment) o;
        return Objects.equals(id, treatment.id) && Objects.equals(treatmentCode, treatment.treatmentCode) && Objects.equals(treatmentDescription, treatment.treatmentDescription) && Objects.equals(treatmentRate, treatment.treatmentRate) && Objects.equals(invoice, treatment.invoice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, treatmentCode, treatmentDescription, treatmentRate, invoice);
    }
}

