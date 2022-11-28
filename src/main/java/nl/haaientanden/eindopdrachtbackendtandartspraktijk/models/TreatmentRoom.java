package nl.haaientanden.eindopdrachtbackendtandartspraktijk.models;

import javax.persistence.*;

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
}
