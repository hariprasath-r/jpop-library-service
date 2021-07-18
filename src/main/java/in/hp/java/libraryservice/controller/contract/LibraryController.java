package in.hp.java.libraryservice.controller.contract;

import in.hp.java.libraryservice.dto.BookDto;
import in.hp.java.libraryservice.dto.LibraryApiResponse;
import in.hp.java.libraryservice.dto.UserDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Library Controller", description = "User and Book data manipulation for the library")
@RequestMapping("/library")
public interface LibraryController {

    // TODO: update this contract
    @Operation(summary = "User login")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful login"),
            @ApiResponse(responseCode = "404", description = "User doesn't exist"),
            @ApiResponse(responseCode = "500", description = "Processing Error")
    })
    @GetMapping("/login")
    boolean login(Long id);

    @Operation(summary = "Gets all Books")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of books"),
            @ApiResponse(responseCode = "500", description = "Processing Error")
    })
    @GetMapping("/books")
    ResponseEntity<LibraryApiResponse<Object>> getBooks();

    @Operation(summary = "Gets a Book")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Book Found"),
            @ApiResponse(responseCode = "404", description = "Book Not Found"),
            @ApiResponse(responseCode = "500", description = "Processing Error")
    })
    @GetMapping("/books/{id}")
    ResponseEntity<LibraryApiResponse<Object>> getBook(@PathVariable Long id);

    @Operation(summary = "Deletes a Book from library and removes it from all users")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "410", description = "Book Deleted"),
            @ApiResponse(responseCode = "500", description = "Processing Error")
    })
    @DeleteMapping("/books/{id}")
    ResponseEntity<Object> deleteBookFromLibrary(@PathVariable Long id);

    // TODO: check if id is needed
    @Operation(summary = "Adds a Book")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Book Added"),
            @ApiResponse(responseCode = "400", description = "Invalid Input"),
            @ApiResponse(responseCode = "500", description = "Processing Error")
    })
    @PostMapping("/books")
    ResponseEntity<Object> addBook(@RequestBody BookDto book);

    @Operation(summary = "Gets all Users")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of users"),
            @ApiResponse(responseCode = "500", description = "Processing Error")
    })
    @GetMapping("/users")
    ResponseEntity<LibraryApiResponse<Object>> getUsers();

    @Operation(summary = "Gets all books of a user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User books"),
            @ApiResponse(responseCode = "404", description = "User doesn't exist"),
            @ApiResponse(responseCode = "500", description = "Processing Error")
    })
    @GetMapping("/users/books")
    ResponseEntity<LibraryApiResponse<Object>> getUserBooks(@PathVariable Long id);

    @Operation(summary = "Adds a User")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User Created"),
            @ApiResponse(responseCode = "400", description = "Invalid Input"),
            @ApiResponse(responseCode = "500", description = "Processing Error")
    })
    @PostMapping("/users")
    ResponseEntity<Object> addUser(@RequestBody UserDto user);

    @Operation(summary = "Updates a User")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User Updated"),
            @ApiResponse(responseCode = "404", description = "User Not Found"),
            @ApiResponse(responseCode = "500", description = "Processing Error")
    })
    @PutMapping("/users")
    ResponseEntity<Object> updateUser(@RequestBody UserDto user);

    @Operation(summary = "Deletes a User")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "410", description = "User Deleted"),
            @ApiResponse(responseCode = "500", description = "Processing Error")
    })
    @DeleteMapping("/users/{id}")
    ResponseEntity<Object> deleteUserFromLibrary(@PathVariable Long id);

    @Operation(summary = "Associates book to an user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Added Book to User"),
            @ApiResponse(responseCode = "404", description = "User or Book doesn't exist"),
            @ApiResponse(responseCode = "500", description = "Processing Error")
    })
    @PostMapping("/users/{user_id}/books/{book_id}")
    ResponseEntity<LibraryApiResponse<Object>> issueBookForUser(
            @PathVariable("book_id") Long bookId,
            @PathVariable("user_id") Long userId);

    @Operation(summary = "Removes book from an user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Book removed from User"),
            @ApiResponse(responseCode = "404", description = "User or Book doesn't exist"),
            @ApiResponse(responseCode = "500", description = "Processing Error")
    })
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
