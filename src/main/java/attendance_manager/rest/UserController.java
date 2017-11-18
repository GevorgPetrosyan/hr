package attendance_manager.rest;

import attendance_manager.domain.User;
import attendance_manager.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Marta Ginosyan
 * Team: Lime
 * Project: greetz3-hr
 * Date: 11/18/17
 */
@RestController("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/username", method = RequestMethod.GET)
    public User getUser(@RequestParam final String userName) {
        return userService.findUserByUsername(userName);
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<User> getAllUsers() {
        return userService.findAllEmployees();
    }

    @RequestMapping(method = RequestMethod.POST)
    public Object create(@RequestBody final User user) {
        userService.save(user);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @RequestMapping(value = "/id", method = RequestMethod.GET)
    public User getUserById(@RequestParam final Long id) {
        return userService.findById(id);
    }

    @RequestMapping(value = "/approve", method = RequestMethod.PUT)
    public User approveUser(@RequestParam final Long id) {
        return userService.approveUser(id);
    }

}
