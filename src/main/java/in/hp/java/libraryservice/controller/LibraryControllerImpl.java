package in.hp.java.libraryservice.controller;

import in.hp.java.libraryservice.controller.contract.LibraryController;
import in.hp.java.libraryservice.dto.BookDto;
import in.hp.java.libraryservice.dto.ApiResponse;
import in.hp.java.libraryservice.dto.UserDto;
import in.hp.java.libraryservice.service.BookService;
import in.hp.java.libraryservice.service.LibraryService;
import in.hp.java.libraryservice.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class LibraryControllerImpl implements LibraryController {

    @Autowired
    private LibraryService libraryService;

    @Autowired
    private UserService userService;

    @Autowired
    private BookService bookService;

    @Override
    public ResponseEntity<ApiResponse<Object>> login(Long id) {
        var user = userService.loginUser(id);
        return generateResponse(user, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ApiResponse<Object>> getBooks() {
        return generateResponse(bookService.getBooks(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ApiResponse<Object>> getBook(Long id) {
        return generateResponse(bookService.getBook(id), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> deleteBookFromLibrary(Long id) {
        libraryService.deleteBook(id);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Object> addBook(BookDto book) {
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Override
    public ResponseEntity<ApiResponse<Object>> getUsers() {
        return generateResponse(userService.getUsers(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ApiResponse<Object>> getUserBooks(Long id) {
        return null;
    }

    @Override
    public ResponseEntity<Object> addUser(UserDto user) {
        userService.addUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Override
    public ResponseEntity<Object> updateUser(UserDto user) {
        userService.updateUser(user);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Override
    public ResponseEntity<Object> deleteUserFromLibrary(Long id) {
        libraryService.deleteUser(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Override
    public ResponseEntity<ApiResponse<Object>> issueBookForUser(Long bookId, Long userId) {
        libraryService.addBookForUser(bookId, userId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Override
    public ResponseEntity<ApiResponse<Object>> removeBookFromUser(Long bookId, Long userId) {
        libraryService.removeBookForUser(bookId, userId);
        return ResponseEntity.status(HttpStatus.GONE).build();
    }
}
