package nl.haaientanden.eindopdrachtbackendtandartspraktijk.dtos;

import javax.validation.constraints.*;

public class TreatmentRoomInputDto {
    @NotNull
    @Min(value = 1)
    @Max(value = 99)
    private Integer roomNumber;

    @NotBlank
    @Size(min = 3, max = 25)
    private String roomColor;

    public TreatmentRoomInputDto(Integer roomNumber, String roomColor) {

        this.roomNumber = roomNumber;
        this.roomColor = roomColor;
    }

    public TreatmentRoomInputDto() {
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
