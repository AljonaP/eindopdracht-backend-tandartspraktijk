package nl.haaientanden.eindopdrachtbackendtandartspraktijk.controllers;

import nl.haaientanden.eindopdrachtbackendtandartspraktijk.dtos.TreatmentRoomDto;
import nl.haaientanden.eindopdrachtbackendtandartspraktijk.dtos.TreatmentRoomInputDto;
import nl.haaientanden.eindopdrachtbackendtandartspraktijk.repositories.TreatmentRoomRepository;
import nl.haaientanden.eindopdrachtbackendtandartspraktijk.services.TreatmentRoomService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.List;

import static nl.haaientanden.eindopdrachtbackendtandartspraktijk.utils.UtilityMethodes.getErrorMessage;

@RestController
@RequestMapping("haaientanden/behandelkamers")
public class TreatmentRoomController {

    private final TreatmentRoomRepository treatmentRoomRepository;
    private final TreatmentRoomService treatmentRoomService;

    public TreatmentRoomController(TreatmentRoomRepository treatmentRoomRepository, TreatmentRoomService treatmentRoomService) {

        this.treatmentRoomRepository = treatmentRoomRepository;
        this.treatmentRoomService = treatmentRoomService;
    }

    @PostMapping("")
    public ResponseEntity<Object> addTreatmentRoom(@Valid @RequestBody TreatmentRoomInputDto treatmentRoomInputDto, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {

            return new ResponseEntity<>(getErrorMessage(bindingResult), HttpStatus.BAD_REQUEST);
        }
        TreatmentRoomDto dto = treatmentRoomService.saveTreatmentRoom(treatmentRoomInputDto);

        return ResponseEntity.created(null).body(dto);
    }

    @GetMapping("")
    public ResponseEntity<List<TreatmentRoomDto>> getAllTreatmentRooms() {

        List<TreatmentRoomDto> dtos = treatmentRoomService.getTreatmentRooms();

        return ResponseEntity.ok().body(dtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getTreatmentRoom(@PathVariable(name = "id") Long id) {

        TreatmentRoomDto dto = treatmentRoomService.getTreatmentRoomById(id);

        return ResponseEntity.ok(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateTreatmentRoom(@PathVariable(name = "id") Long id, @RequestBody TreatmentRoomInputDto newTreatmentRoom) {

        TreatmentRoomDto dto = treatmentRoomService.updateTreatmentRoom(id, newTreatmentRoom);

        return ResponseEntity.ok().body(dto);
    }

    @DeleteMapping("/{id}")
    public void deleteTreatmentRoom(@PathVariable(name = "id") Long id) {

        treatmentRoomService.deleteTreatmentRoomById(id);
    }
}
