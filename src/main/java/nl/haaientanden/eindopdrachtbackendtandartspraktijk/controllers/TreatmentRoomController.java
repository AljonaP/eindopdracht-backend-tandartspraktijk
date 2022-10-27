package nl.haaientanden.eindopdrachtbackendtandartspraktijk.controllers;

import nl.haaientanden.eindopdrachtbackendtandartspraktijk.dtos.TreatmentRoomDto;
import nl.haaientanden.eindopdrachtbackendtandartspraktijk.dtos.TreatmentRoomInputDto;
import nl.haaientanden.eindopdrachtbackendtandartspraktijk.repositories.TreatmentRoomRepository;
import nl.haaientanden.eindopdrachtbackendtandartspraktijk.services.TreatmentRoomService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

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
        if(bindingResult.hasErrors()) {
            return new ResponseEntity<>(getErrorMessage(bindingResult), HttpStatus.BAD_REQUEST);
        }
        TreatmentRoomDto dto = treatmentRoomService.saveTreatmentRoom(treatmentRoomInputDto);
        return ResponseEntity.created(null).body(dto);
    }


}
