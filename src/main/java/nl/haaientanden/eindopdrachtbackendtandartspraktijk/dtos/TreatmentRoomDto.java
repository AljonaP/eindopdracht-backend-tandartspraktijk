package nl.haaientanden.eindopdrachtbackendtandartspraktijk.dtos;

public class TreatmentRoomDto {

    private Long id;
    private Integer roomNumber;
    private String roomColor;

    public TreatmentRoomDto(Long id,
                            Integer roomNumber,
                            String roomColor) {
        this.id = id;
        this.roomNumber = roomNumber;
        this.roomColor = roomColor;
    }

    public TreatmentRoomDto() {
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
        this.roomColor = roomColor;
    }
}
