package nl.haaientanden.eindopdrachtbackendtandartspraktijk.services;

import nl.haaientanden.eindopdrachtbackendtandartspraktijk.dtos.TreatmentRoomDto;
import nl.haaientanden.eindopdrachtbackendtandartspraktijk.dtos.TreatmentRoomInputDto;
import nl.haaientanden.eindopdrachtbackendtandartspraktijk.exceptions.RecordNotFoundException;
import nl.haaientanden.eindopdrachtbackendtandartspraktijk.models.TreatmentRoom;
import nl.haaientanden.eindopdrachtbackendtandartspraktijk.repositories.TreatmentRoomRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static nl.haaientanden.eindopdrachtbackendtandartspraktijk.services.TreatmentRoomService.transferToDto;
import static nl.haaientanden.eindopdrachtbackendtandartspraktijk.services.TreatmentRoomService.transferToTreatmentRoom;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TreatmentRoomServiceTest {

    @Mock
    TreatmentRoomRepository mockTreatmentRoomRepository;

    @InjectMocks
    TreatmentRoomService treatmentRoomService;

    TreatmentRoomInputDto treatmentRoomInputDto1;
    TreatmentRoomInputDto treatmentRoomInputDto2;
    TreatmentRoomInputDto treatmentRoomInputDto3;
    TreatmentRoom newTreatmentRoom;

    @Captor
    ArgumentCaptor<TreatmentRoom> captor;
    TreatmentRoom treatmentRoom1;
    TreatmentRoom treatmentRoom2;
    TreatmentRoom treatmentRoom3;

    @BeforeEach
    void setUp() {

        treatmentRoom1 = new TreatmentRoom(1L, 1, "orange");
        treatmentRoom2 = new TreatmentRoom(2L, 2, "blue");
        treatmentRoom3 = new TreatmentRoom(3L, 3, "yellow");
        treatmentRoomInputDto1 = new TreatmentRoomInputDto(1, "orange");
        treatmentRoomInputDto2 = new TreatmentRoomInputDto(2, "blue");
        treatmentRoomInputDto3 = new TreatmentRoomInputDto(3, "yellow");
        newTreatmentRoom = new TreatmentRoom();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testSaveTreatmentRoom() {

        when(mockTreatmentRoomRepository.save(any(TreatmentRoom.class))).thenReturn(treatmentRoom1);

        treatmentRoomService.saveTreatmentRoom(treatmentRoomInputDto1);
        verify(mockTreatmentRoomRepository, times(1)).save(captor.capture());
        TreatmentRoom treatmentRoom = captor.getValue();

        assertEquals(treatmentRoom1.getRoomNumber(), treatmentRoom.getRoomNumber());
        assertEquals(treatmentRoom1.getRoomColor(), treatmentRoom.getRoomColor());
    }

    @Test
    void testGetTreatmentRooms() {
        when(mockTreatmentRoomRepository.findAll()).thenReturn(List.of(treatmentRoom1, treatmentRoom2, treatmentRoom3));

        List<TreatmentRoom> treatmentRooms = mockTreatmentRoomRepository.findAll();
        List<TreatmentRoomDto> dtos = treatmentRoomService.getTreatmentRooms();

        assertEquals(treatmentRooms.get(0).getId(), dtos.get(0).getId());
        assertEquals(treatmentRooms.get(0).getRoomNumber(), dtos.get(0).getRoomNumber());
        assertEquals(treatmentRooms.get(0).getRoomColor(), dtos.get(0).getRoomColor());
        assertEquals(treatmentRooms.get(1).getId(), dtos.get(1).getId());
        assertEquals(treatmentRooms.get(1).getRoomNumber(), dtos.get(1).getRoomNumber());
        assertEquals(treatmentRooms.get(1).getRoomColor(), dtos.get(1).getRoomColor());
        assertEquals(treatmentRooms.get(2).getId(), dtos.get(2).getId());
        assertEquals(treatmentRooms.get(2).getRoomNumber(), dtos.get(2).getRoomNumber());
        assertEquals(treatmentRooms.get(2).getRoomColor(), dtos.get(2).getRoomColor());

    }

    @Test
    void testGetTreatmentRoomById() {
        when(mockTreatmentRoomRepository.findById(1L)).thenReturn(Optional.of(treatmentRoom1));

        TreatmentRoom treatmentRoom = mockTreatmentRoomRepository.findById(1L).get();
        TreatmentRoomDto treatmentRoomDto = treatmentRoomService.getTreatmentRoomById(1L);

        assertEquals(treatmentRoom.getRoomNumber(), treatmentRoomDto.getRoomNumber());
        assertEquals(treatmentRoom.getRoomColor(), treatmentRoomDto.getRoomColor());
    }

    @Test
    void testUpdateTreatmentRoom() {
        when(mockTreatmentRoomRepository.findById(1L)).thenReturn(Optional.of(treatmentRoom1));

        TreatmentRoomInputDto treatmentRoomInputDto = new TreatmentRoomInputDto(2, "purple");

        when(mockTreatmentRoomRepository.save(any(TreatmentRoom.class))).thenReturn(treatmentRoom1);

        treatmentRoomService.updateTreatmentRoom(1L, treatmentRoomInputDto);
        verify(mockTreatmentRoomRepository, times(1)).save(captor.capture());
        TreatmentRoom updatedTreatmentRoom = captor.getValue();

        assertEquals(treatmentRoomInputDto.getRoomNumber(), updatedTreatmentRoom.getRoomNumber());
        assertEquals(treatmentRoomInputDto.getRoomColor(), updatedTreatmentRoom.getRoomColor());
    }

    @Test
    void testRecordNotFoundException() {
        assertThrows(RecordNotFoundException.class, () -> treatmentRoomService.getTreatmentRoomById(null));
        assertThrows(RecordNotFoundException.class, () -> treatmentRoomService.updateTreatmentRoom(null, treatmentRoomInputDto2));
    }

    @Test
    void testDeleteTreatmentRoomById() {
        treatmentRoomService.deleteTreatmentRoomById(3L);

        verify(mockTreatmentRoomRepository).deleteById(3L);
    }

    @Test
    void testTransferToTreatmentRoom() {
        TreatmentRoom newTreatmentRoom = transferToTreatmentRoom(treatmentRoomInputDto2);

        assertEquals(treatmentRoomInputDto2.getRoomNumber(), newTreatmentRoom.getRoomNumber());
        assertEquals(treatmentRoomInputDto2.getRoomColor(), newTreatmentRoom.getRoomColor());
    }

    @Test
    void testTransferToDto() {
        TreatmentRoomDto newTreatmentRoomDto = transferToDto(treatmentRoom3);

        assertEquals(treatmentRoom3.getId(), newTreatmentRoomDto.getId());
        assertEquals(treatmentRoom3.getRoomNumber(), newTreatmentRoomDto.getRoomNumber());
        assertEquals(treatmentRoom3.getRoomColor(), newTreatmentRoomDto.getRoomColor());
    }
}