package in.hp.java.libraryservice.api;

import in.hp.java.libraryservice.dto.ApiResponse;
import in.hp.java.libraryservice.dto.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "${user-service.name}", path = "${user-service.path}")
public interface UserServiceClient {

    @PostMapping
    ResponseEntity<Object> addUser(@RequestBody UserDto user);

    @PutMapping
    ResponseEntity<Object> updateUser(@RequestBody UserDto user);

    @GetMapping
    ResponseEntity<ApiResponse<List<UserDto>>> getUsers();

    @GetMapping("/{id}")
    ResponseEntity<ApiResponse<UserDto>> getUser(@PathVariable Long id);

    @DeleteMapping("/{id}")
    ResponseEntity<Object> deleteUser(@PathVariable Long id);
}
