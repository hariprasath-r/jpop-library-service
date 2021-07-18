package in.hp.java.libraryservice.service;

import in.hp.java.libraryservice.api.BookServiceClient;
import in.hp.java.libraryservice.dto.BookDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class BookService {

    @Autowired
    private BookServiceClient bookServiceClient;

    public List<BookDto> getBooks() {
        var bookResponse = bookServiceClient.getBooks();
        var books = (List<BookDto>) bookResponse.getBody().getResponse();
        return books;
    }

    public BookDto getBook(Long bookId) {
        var bookResponse = bookServiceClient.getBook(bookId);
        var book = (BookDto) bookResponse.getBody().getResponse();
        return book;
    }

    public void addBook(BookDto book) {
        var responseEntity = bookServiceClient.addBook(book);
        if (responseEntity.getStatusCode().equals(HttpStatus.CREATED)) {
            log.info("Book added.");
        } else {
            // log and throw exception
        }
    }

    public void deleteBook(Long bookId) {
        var responseEntity = bookServiceClient.deleteBook(bookId);
        if (responseEntity.getStatusCode().equals(HttpStatus.GONE)) {
            log.info("Book deleted.");
        } else {
            // log and throw exception
        }
    }
}
