package nl.haaientanden.eindopdrachtbackendtandartspraktijk.dtos;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class TreatmentRoomInputDto {
    @NotNull
    @Size(min = 1, max = 2)
    private int roomNumber;

    @NotBlank
    @Size(min = 3, max = 25)
    private String roomColor;

    public TreatmentRoomInputDto(Long id, int roomNumber, String roomColor) {

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
