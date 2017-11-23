package attendance_manager.rest;

import attendance_manager.domain.IndividualTimeOff;
import attendance_manager.service.Individualtimeoff.IndividualTimeOffService;
import attendance_manager.service.Individualtimeoff.dto.IndividualTimeOffDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * @author sergey, created on 11/18/17.
 */
@RestController("/individualtimeoff")
public class IndividualTimeOffController {

    @Autowired
    private IndividualTimeOffService individualTimeOffService;

    @PostMapping
    public ResponseEntity createIndividualTimeOff(@RequestBody final IndividualTimeOffDTO individualTimeOffDTO) throws URISyntaxException {
        final Long id = individualTimeOffService.createWithDto(individualTimeOffDTO);
        return ResponseEntity.created(new URI(String.format("/individualtimeoff/%d", id))).build();
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<IndividualTimeOffDTO> getIndividualTimeOff(@PathVariable("id") final Long id) {
        final IndividualTimeOff individualTimeOff = individualTimeOffService.getById(id);
        final IndividualTimeOffDTO individualTimeOffDTO = new IndividualTimeOffDTO();
        individualTimeOffDTO.setComment(individualTimeOff.getComment());
        individualTimeOffDTO.setEmployeeId(individualTimeOff.getId());
        individualTimeOffDTO.setStartDate(individualTimeOff.getStart().toString());
        individualTimeOffDTO.setEndDate(individualTimeOff.getEnd().toString());
        return ResponseEntity.ok(individualTimeOffDTO);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity updateIndividualTimeOff(@PathVariable("id") final Long id, @RequestBody final IndividualTimeOffDTO individualTimeOffDTO) {
        individualTimeOffService.update(id, individualTimeOffDTO);
        return ResponseEntity.accepted().build();
    }

    @DeleteMapping(path = "/{id}")
    public void deleteIndividualTimeOff(@PathVariable("id") final Long id) {
        individualTimeOffService.delete(id);
    }
}
