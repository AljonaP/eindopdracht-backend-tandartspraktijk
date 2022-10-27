package nl.haaientanden.eindopdrachtbackendtandartspraktijk.services;


import nl.haaientanden.eindopdrachtbackendtandartspraktijk.dtos.TreatmentRoomDto;
import nl.haaientanden.eindopdrachtbackendtandartspraktijk.dtos.TreatmentRoomInputDto;
import nl.haaientanden.eindopdrachtbackendtandartspraktijk.exceptions.RecordNotFoundException;
import nl.haaientanden.eindopdrachtbackendtandartspraktijk.models.TreatmentRoom;
import nl.haaientanden.eindopdrachtbackendtandartspraktijk.repositories.TreatmentRoomRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.ArrayList;
import java.util.List;

@Service
public class TreatmentRoomService {
    private final TreatmentRoomRepository treatmentRoomRepository;

    public TreatmentRoomService(TreatmentRoomRepository treatmentRoomRepository) {
        this.treatmentRoomRepository = treatmentRoomRepository;
    }

    public TreatmentRoomDto saveTreatmentRoom(TreatmentRoomInputDto dto) {

        TreatmentRoom treatmentRoom = transferToTreatmentRoom(dto);
        treatmentRoomRepository.save(treatmentRoom);

        return transferToDto(treatmentRoom);
    }

    public List<TreatmentRoomDto> getTreatmentRooms() {

        List<TreatmentRoom> treatmentRooms = treatmentRoomRepository.findAll();
        List<TreatmentRoomDto> dtos = new ArrayList<>();
        for (TreatmentRoom treatmentRoom : treatmentRooms) {
            dtos.add(transferToDto(treatmentRoom));
        }
        return dtos;
    }

    public TreatmentRoomDto getTreatmentRoomById(Long id) {

        if(treatmentRoomRepository.findById(id).isPresent()){
            TreatmentRoom treatmentRoom = treatmentRoomRepository.findById(id).get();
            return transferToDto(treatmentRoom);
        } else {
            throw new RecordNotFoundException("The entered value isn't correct or doesn't exist. Search again with another value.");
        }
    }

    public TreatmentRoomDto updateTreatmentRoom(Long id, TreatmentRoomInputDto inputDto) {
        if(treatmentRoomRepository.findById(id).isPresent()) {
            TreatmentRoom treatmentRoom = treatmentRoomRepository.findById(id).get();
            TreatmentRoom treatmentRoom1 = transferToTreatmentRoom(inputDto);
            treatmentRoom1.setId(treatmentRoom.getId());

            treatmentRoomRepository.save(treatmentRoom1);

            return transferToDto(treatmentRoom1);
        } else {
            throw new RecordNotFoundException("geen treatment is gevonden");
        }
    }

    public void deleteTreatmentRoomById(@RequestBody Long id) {
        treatmentRoomRepository.deleteById(id);
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

        return dto;
    }
}
