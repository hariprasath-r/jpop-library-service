package in.hp.java.libraryservice.controller;

import in.hp.java.libraryservice.controller.contract.LibraryController;
import in.hp.java.libraryservice.dto.BookDto;
import in.hp.java.libraryservice.dto.ApiResponse;
import in.hp.java.libraryservice.dto.UserDto;
import in.hp.java.libraryservice.service.LibraryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class LibraryControllerImpl implements LibraryController {

    @Autowired
    private LibraryService libraryService;

    @Override
    public boolean login(Long id) {
        return false;
    }

    @Override
    public ResponseEntity<ApiResponse<Object>> getBooks() {
        return null;
    }

    @Override
    public ResponseEntity<ApiResponse<Object>> getBook(Long id) {
        return null;
    }

    @Override
    public ResponseEntity<Object> deleteBookFromLibrary(Long id) {
        return null;
    }

    @Override
    public ResponseEntity<Object> addBook(BookDto book) {
        return null;
    }

    @Override
    public ResponseEntity<ApiResponse<Object>> getUsers() {
        return null;
    }

    @Override
    public ResponseEntity<ApiResponse<Object>> getUserBooks(Long id) {
        return null;
    }

    @Override
    public ResponseEntity<Object> addUser(UserDto user) {
        return null;
    }

    @Override
    public ResponseEntity<Object> updateUser(UserDto user) {
        return null;
    }

    @Override
    public ResponseEntity<Object> deleteUserFromLibrary(Long id) {
        return null;
    }

    @Override
    public ResponseEntity<ApiResponse<Object>> issueBookForUser(Long bookId, Long userId) {
        return null;
    }

    @Override
    public ResponseEntity<ApiResponse<Object>> removeBookFromUser(Long bookId, Long userId) {
        return null;
    }
}
