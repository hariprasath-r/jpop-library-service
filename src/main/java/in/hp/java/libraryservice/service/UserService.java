package in.hp.java.libraryservice.service;

import in.hp.java.libraryservice.api.UserServiceClient;
import in.hp.java.libraryservice.dto.UserDto;
import in.hp.java.libraryservice.exception.ResourceNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class UserService {

    @Autowired
    private UserServiceClient userServiceClient;

    public UserDto getUser(Long userId) {
        log.info("Calling user service for user:: {}", userId);
        var responseEntity = userServiceClient.getUser(userId);
        log.info("User service response received with status:: {}", responseEntity.getStatusCodeValue());

        var body = Optional.ofNullable(responseEntity.getBody());
        if (body.isPresent()) {
            var user = body.get().getResponse();
            log.info("Logged in user <{}>", user);
            return user;
        } else {
            log.error("Response Body Empty.");
            throw new ResourceNotFoundException("User Not Found");
        }
    }

    public List<UserDto> getUsers() {
        log.info("Retrieving all Users");
        var responseEntity = userServiceClient.getUsers();
        log.info("User service response received with status :: {}", responseEntity.getStatusCodeValue());

        var body = Optional.ofNullable(responseEntity.getBody());
        if (body.isPresent()) {
            var users = body.get().getResponse();
            log.info("Users received :: {}", users.size());
            return users;
        } else {
            log.error("Response Body Empty.");
            throw new ResourceNotFoundException("No Users Found");
        }
    }

    public void addUser(UserDto userDto) {
        userServiceClient.addUser(userDto);
        log.info("User created.");
    }

    public void updateUser(UserDto userDto) {
        userServiceClient.updateUser(userDto);
        log.info("User updated.");
    }

    public void deleteUser(Long userId) {
        userServiceClient.deleteUser(userId);
        log.info("User deleted.");
    }
}
