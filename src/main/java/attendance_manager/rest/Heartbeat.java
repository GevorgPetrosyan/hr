package attendance_manager.rest;

import attendance_manager.service.Individualtimeoff.IndividualTimeOffService;
import attendance_manager.service.dto.TimeOffTypeService;
import attendance_manager.service.mail.EmailSendingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Heartbeat {

    @Autowired
    private EmailSendingService emailSendingService;

    @Autowired
    private IndividualTimeOffService individualTimeOffService;

    @Autowired
    private TimeOffTypeService timeOffTypeService;

    @GetMapping(value = "/heartbeat")
    @ResponseBody
    public String heartBeat() {
        return "ALIVE";
    }
}
