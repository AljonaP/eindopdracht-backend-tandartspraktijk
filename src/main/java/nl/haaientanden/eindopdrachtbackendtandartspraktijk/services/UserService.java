package nl.haaientanden.eindopdrachtbackendtandartspraktijk.services;

import nl.haaientanden.eindopdrachtbackendtandartspraktijk.dtos.UserDto;
import nl.haaientanden.eindopdrachtbackendtandartspraktijk.exceptions.BadRequestException;
import nl.haaientanden.eindopdrachtbackendtandartspraktijk.exceptions.RecordNotFoundException;
import nl.haaientanden.eindopdrachtbackendtandartspraktijk.models.Role;
import nl.haaientanden.eindopdrachtbackendtandartspraktijk.models.User;
import nl.haaientanden.eindopdrachtbackendtandartspraktijk.repositories.RoleRepository;
import nl.haaientanden.eindopdrachtbackendtandartspraktijk.repositories.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    public final UserRepository userRepository;
    private final PasswordEncoder encoder;
    public final RoleRepository roleRepository;

    public UserService(UserRepository userRepository, PasswordEncoder encoder, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.encoder = encoder;
        this.roleRepository = roleRepository;
    }

    public UserDto saveUser(UserDto userDto) {
        if (userRepository.findById(userDto.getUsername()).isEmpty()) {
            User user = transferToUser(userDto);
            userRepository.save(transferToUser(userDto));
            return transferToDto(user);
        } else {
            throw new BadRequestException("Entered username already exist. Try another username.");
        }
    }

    public List<UserDto> getUsers() {
        return transferUserListToDtoList(userRepository.findAll());
    }

    public UserDto getUserById(String username) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(userRepository.findById(username).isPresent()){
            if (username.equals(authentication.getName())) {
                User user = userRepository.findById(username).get();
                return transferToDto(user);
            }

        }
        throw new RecordNotFoundException("The entered username '" + username + "' isn't correct, doesn't exist or you have used not right credentials. Search again with another username or credentials.");
    }

    public UserDto updateUser(String username, UserDto userDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(userRepository.findById(username).isPresent()) {
            User user = userRepository.findById(username).get();
            if (userDto.getUsername().equals(authentication.getName())) {
                User user1 = transferToUser(userDto);
                user1.setUsername(user.getUsername());

                userRepository.save(user1);

                return transferToDto(user1);
            }
        }
        throw new RecordNotFoundException("Used Token is from another user with another username. Try again with own credentials for username '" + username + "'.");
    }

    public void deleteUserByIdUsername(@RequestBody String username) {
        userRepository.deleteById(username);
    }

    public User transferToUser(UserDto userDto) {
        var user = new User();
        user.setUsername(userDto.getUsername());
        user.setPassword(encoder.encode(userDto.password));
        List<Role> userRoles = new ArrayList<>();
        for (String rolename : userDto.roles) {
            if (roleRepository.findById(rolename).isPresent()) {
                Optional<Role> optionalRole = roleRepository.findById(rolename);
                userRoles.add(optionalRole.get());
            } else {
                throw new RecordNotFoundException("Entered role doesn't exist.");
            }
        }
        user.setRoles(userRoles);

        return user;
    }

    public static UserDto transferToDto(User user) {
        UserDto userDto = new UserDto();

        userDto.setUsername(user.getUsername());
        userDto.setPassword(user.getPassword());

        List<String> userRoles = new ArrayList<>();
        for (Role role : user.getRoles()) {
            userRoles.add(role.getRolename());
        }
        userDto.setRoles(userRoles);

        return userDto;
    }

    public static List<UserDto> transferUserListToDtoList(List<User> users) {
        List<UserDto> userDtoList = new ArrayList<>();

        for (User user : users) {
            UserDto userDto = transferToDto(user);
            userDtoList.add(userDto);
        }
        return userDtoList;
    }
}
