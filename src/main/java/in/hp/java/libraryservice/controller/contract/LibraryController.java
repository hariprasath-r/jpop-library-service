package in.hp.java.libraryservice.controller.contract;

import in.hp.java.libraryservice.dto.ApiResponse;
import in.hp.java.libraryservice.dto.BookDto;
import in.hp.java.libraryservice.dto.UserDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.ZoneId;

@Tag(name = "Library Controller", description = "User and Book data manipulation for the library")
@RequestMapping("/library")
public interface LibraryController {

    // TODO: update this contract
    @Operation(summary = "User login")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Successful login"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "User doesn't exist"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "Processing Error")
    })
    @GetMapping("/login/{id}")
    ResponseEntity<ApiResponse<Object>> login(@PathVariable Long id);

    @Operation(summary = "Gets all Books")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "List of books"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "Processing Error")
    })
    @GetMapping("/books")
    ResponseEntity<ApiResponse<Object>> getBooks();

    @Operation(summary = "Gets a Book")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Book Found"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Book Not Found"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "Processing Error")
    })
    @GetMapping("/books/{id}")
    ResponseEntity<ApiResponse<Object>> getBook(@PathVariable Long id);

    @Operation(summary = "Deletes a Book from library and removes it from all users")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "410", description = "Book Deleted"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "Processing Error")
    })
    @DeleteMapping("/books/{id}")
    ResponseEntity<Object> deleteBookFromLibrary(@PathVariable Long id);

    // TODO: check if id is needed
    @Operation(summary = "Adds a Book")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "201", description = "Book Added"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Invalid Input"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "Processing Error")
    })
    @PostMapping("/books")
    ResponseEntity<Object> addBook(@RequestBody BookDto book);

    @Operation(summary = "Gets all Users")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "List of users"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "Processing Error")
    })
    @GetMapping("/users")
    ResponseEntity<ApiResponse<Object>> getUsers();

    @Operation(summary = "Gets all books of a user")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "User books"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "User doesn't exist"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "Processing Error")
    })
    @GetMapping("/users/books")
    ResponseEntity<ApiResponse<Object>> getUserBooks(@PathVariable Long id);

    @Operation(summary = "Adds a User")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "201", description = "User Created"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Invalid Input"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "Processing Error")
    })
    @PostMapping("/users")
    ResponseEntity<Object> addUser(@RequestBody UserDto user);

    @Operation(summary = "Updates a User")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "User Updated"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "User Not Found"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "Processing Error")
    })
    @PutMapping("/users")
    ResponseEntity<Object> updateUser(@RequestBody UserDto user);

    @Operation(summary = "Deletes a User")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "410", description = "User Deleted"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "Processing Error")
    })
    @DeleteMapping("/users/{id}")
    ResponseEntity<Object> deleteUserFromLibrary(@PathVariable Long id);

    @Operation(summary = "Associates book to an user")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Added Book to User"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "User or Book doesn't exist"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "Processing Error")
    })
    @PostMapping("/users/{user_id}/books/{book_id}")
    ResponseEntity<ApiResponse<Object>> issueBookForUser(
            @PathVariable("book_id") Long bookId,
            @PathVariable("user_id") Long userId);

    @Operation(summary = "Removes book from an user")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Book removed from User"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "User or Book doesn't exist"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "Processing Error")
    })
    @DeleteMapping("/users/{user_id}/books/{book_id}")
    ResponseEntity<ApiResponse<Object>> removeBookFromUser(
            @PathVariable("book_id") Long bookId,
            @PathVariable("user_id") Long userId);

    default <T> ResponseEntity<ApiResponse<Object>> generateResponse(T response, HttpStatus httpStatus) {
        var libraryApiResponse = ApiResponse.builder()
                .response(response)
                .timestamp(LocalDateTime.now(ZoneId.of(ZoneId.SHORT_IDS.get("IST"))))
                .build();

        return ResponseEntity.status(httpStatus)
                .body(libraryApiResponse);
    }
}
