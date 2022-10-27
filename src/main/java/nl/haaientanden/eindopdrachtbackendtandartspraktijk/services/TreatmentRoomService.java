package nl.haaientanden.eindopdrachtbackendtandartspraktijk.services;

import nl.haaientanden.eindopdrachtbackendtandartspraktijk.dtos.TreatmentRoomDto;
import nl.haaientanden.eindopdrachtbackendtandartspraktijk.dtos.TreatmentRoomInputDto;
import nl.haaientanden.eindopdrachtbackendtandartspraktijk.models.TreatmentRoom;
import nl.haaientanden.eindopdrachtbackendtandartspraktijk.repositories.TreatmentRoomRepository;
import org.springframework.stereotype.Service;

@Service
public class TreatmentRoomService {
    private final TreatmentRoomRepository treatmentRoomRepository;

    public TreatmentRoomService(TreatmentRoomRepository treatmentRoomRepository) {
        this.treatmentRoomRepository = treatmentRoomRepository;
    }

    public static TreatmentRoom transferToTreatmentRoom(TreatmentRoomInputDto dto) {
        var treatmentRoom = new TreatmentRoom();

        treatmentRoom.setRoomNumber(dto.getRoomNumber());;
        treatmentRoom.setRoomColor(dto.getRoomColor());

        return treatmentRoom;
    }

    public static TreatmentRoomDto transferToDto(TreatmentRoom treatmentRoom) {
        TreatmentRoomDto dto = new TreatmentRoomDto();

        dto.setId(treatmentRoom.getId());
        dto.setRoomNumber(treatmentRoom.getRoomNumber());
        dto.setRoomColor(treatmentRoom.getRoomColor());
    }
}
