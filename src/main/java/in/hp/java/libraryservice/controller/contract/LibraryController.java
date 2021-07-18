package in.hp.java.libraryservice.controller.contract;

import in.hp.java.libraryservice.dto.BookDto;
import in.hp.java.libraryservice.dto.LibraryApiResponse;
import in.hp.java.libraryservice.dto.UserDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/library")
public interface LibraryController {

    // TODO: update this contract
    @GetMapping("/login")
    boolean login(Long id);

    @GetMapping("/books")
    ResponseEntity<LibraryApiResponse<Object>> getBooks();

    @GetMapping("/books/{id}")
    ResponseEntity<LibraryApiResponse<Object>> getBook(@PathVariable Long id);

    @DeleteMapping("/books/{id}")
    ResponseEntity<Object> deleteBookFromLibrary(@PathVariable Long id);

    // TODO: check if id is needed
    @PostMapping("/books")
    ResponseEntity<Object> addBook(@RequestBody BookDto book);

    @GetMapping("/users")
    ResponseEntity<LibraryApiResponse<Object>> getUsers();

    @GetMapping("/users")
    ResponseEntity<LibraryApiResponse<Object>> getUserBooks(@PathVariable Long id);

    @PostMapping("/users")
    ResponseEntity<Object> addUser(@RequestBody UserDto user);

    @PutMapping("/users")
    ResponseEntity<Object> updateUser(@RequestBody UserDto user);

    @DeleteMapping("/users/{id}")
    ResponseEntity<Object> deleteUserFromLibrary(@PathVariable Long id);

    @PostMapping("/users/{user_id}/books/{book_id}")
    ResponseEntity<LibraryApiResponse<Object>> issueBookForUser(
            @PathVariable("book_id") Long bookId,
            @PathVariable("user_id") Long userId);

    @DeleteMapping("/users/{user_id}/books/{book_id}")
    ResponseEntity<LibraryApiResponse<Object>> removeBookFromUser(
            @PathVariable("book_id") Long bookId,
            @PathVariable("user_id") Long userId);

    default <T> ResponseEntity<LibraryApiResponse<Object>> generateResponse(T response, HttpStatus httpStatus) {
        var libraryApiResponse = LibraryApiResponse.builder()
                .response(response)
                .build();

        return ResponseEntity.status(httpStatus)
                .body(libraryApiResponse);
    }
}
