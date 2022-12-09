package nl.haaientanden.eindopdrachtbackendtandartspraktijk.controllers;

import nl.haaientanden.eindopdrachtbackendtandartspraktijk.dtos.UserDto;
import nl.haaientanden.eindopdrachtbackendtandartspraktijk.repositories.UserRepository;
import nl.haaientanden.eindopdrachtbackendtandartspraktijk.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static nl.haaientanden.eindopdrachtbackendtandartspraktijk.utils.UtilityMethodes.getErrorMessage;

@RestController
@RequestMapping("/haaientanden/users")
public class UserController {
    private final UserRepository userRepository;
    private final UserService userService;

    public UserController(UserRepository userRepository, UserService userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @PostMapping("")
    public ResponseEntity<Object> addUser(@Valid @RequestBody UserDto userDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(getErrorMessage(bindingResult), HttpStatus.BAD_REQUEST);
        }
        UserDto dto = userService.saveUser(userDto);

        return ResponseEntity.created(null).body(dto);
    }

    @GetMapping("")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<UserDto> dtos = userService.getUsers();

        return ResponseEntity.ok().body(dtos);
    }

    @GetMapping("/{username}")
    public ResponseEntity<Object> getUser(@PathVariable(name = "username") String username) {
        UserDto dto = userService.getUserById(username);

        return ResponseEntity.ok(dto);
    }

    @PutMapping("/{username}")
    public ResponseEntity<Object> updateUser(@PathVariable(name = "username") String username, @RequestBody UserDto newUser) {
        UserDto dto = userService.updateUser(username, newUser);

        return ResponseEntity.ok().body(dto);
    }

    @DeleteMapping("/{username}")
    public void deleteUser(@PathVariable(name = "username") String username) {
        userService.deleteUserByIdUsername(username);
    }
}
