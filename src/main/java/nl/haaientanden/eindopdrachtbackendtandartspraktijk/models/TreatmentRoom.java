package nl.haaientanden.eindopdrachtbackendtandartspraktijk.models;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "treatment_rooms")
public class TreatmentRoom {

    @Id
    @GeneratedValue
    private Long id;
    private Integer roomNumber;
    private String roomColor;
    @OneToOne(mappedBy = "treatmentRoom")
    Appointment appointment;

    public TreatmentRoom(Long id,
                         Integer roomNumber,
                         String roomColor) {

        this.id = id;
        this.roomNumber = roomNumber;
        this.roomColor = roomColor;
    }

    public TreatmentRoom() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getRoomColor() {
        return roomColor;
    }

    public void setRoomColor(String roomColor) {
        this.roomColor = roomColor.toLowerCase();
    }

    public Appointment getAppointment() {
        return appointment;
    }

    public void setAppointment(Appointment appointment) {
        this.appointment = appointment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TreatmentRoom that = (TreatmentRoom) o;
        return Objects.equals(getId(), that.getId()) && Objects.equals(getRoomNumber(), that.getRoomNumber()) && Objects.equals(getRoomColor(), that.getRoomColor()) && Objects.equals(getAppointment(), that.getAppointment());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getRoomNumber(), getRoomColor(), getAppointment());
    }
}
