package in.hp.java.libraryservice.api;

import in.hp.java.libraryservice.dto.ApiResponse;
import in.hp.java.libraryservice.dto.BookDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "${book-service.name}", path = "${book-service.root-path}")
public interface BookServiceClient {

    @GetMapping
    ResponseEntity<ApiResponse<List<BookDto>>> getBooks();

    @PostMapping("/filter")
    ResponseEntity<ApiResponse<List<BookDto>>> getBooks(@RequestBody List<Long> bookIdentifiers);

    @GetMapping("/{id}")
    ResponseEntity<ApiResponse<BookDto>> getBook(@PathVariable Long id);

    @PostMapping
    ResponseEntity<Object> addBook(@RequestBody BookDto book);

    @DeleteMapping("/{id}")
    ResponseEntity<Object> deleteBook(@PathVariable Long id);
}
