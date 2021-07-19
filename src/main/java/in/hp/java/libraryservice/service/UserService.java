package in.hp.java.libraryservice.service;

import in.hp.java.libraryservice.api.UserServiceClient;
import in.hp.java.libraryservice.dto.UserDto;
import in.hp.java.libraryservice.exception.ResourceNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class UserService {

    @Autowired
    private UserServiceClient userServiceClient;

    public UserDto loginUser(Long userId) {
        log.info("Calling user service");
        var responseEntity = userServiceClient.getUser(userId);
        log.info("User service response received with status:: {}", responseEntity.getStatusCodeValue());

        var body = Optional.ofNullable(responseEntity.getBody());
        if (body.isPresent()) {
            var user = body.get().getResponse();
            log.info("Logged in user <{}>", user);
            return user;
        } else {
            throw new ResourceNotFoundException("User Not Found");
        }
    }

    public List<UserDto> getUsers() {
        var response = userServiceClient.getUsers().getBody();
        var users = (List<UserDto>) response.getResponse();
        return users;
    }

    public UserDto getUser(Long userId) {
        var response = userServiceClient.getUser(userId).getBody();
        var user = (UserDto) response.getResponse();
        return user;
    }

    public void addUser(UserDto userDto) {
        var responseEntity = userServiceClient.addUser(userDto);
        if (responseEntity.getStatusCode().equals(HttpStatus.CREATED)) {
            log.info("User created.");
        } else {
            // log and throw exception
        }
    }

    public void updateUser(UserDto userDto) {
        var responseEntity = userServiceClient.updateUser(userDto);
        if (responseEntity.getStatusCode().equals(HttpStatus.OK)) {
            log.info("User updated");
        } else {
            // log and throw exception
        }
    }

    public void deleteUser(Long userId) {
        var responseEntity = userServiceClient.deleteUser(userId);
        if (responseEntity.getStatusCode().equals(HttpStatus.GONE)) {
            log.info("User Deleted.");
        } else {
            // log and throw exception
        }
    }
}
