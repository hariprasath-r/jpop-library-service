package in.hp.java.libraryservice.service;

import in.hp.java.libraryservice.api.BookServiceClient;
import in.hp.java.libraryservice.dto.BookDto;
import in.hp.java.libraryservice.exception.ResourceNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class BookService {

    @Autowired
    private BookServiceClient bookServiceClient;

    public List<BookDto> getBooks() {
        log.info("Retrieving all Books");
        var responseEntity = bookServiceClient.getBooks();
        log.info("Get Books response received with status :: {}", responseEntity.getStatusCodeValue());

        var body = Optional.ofNullable(responseEntity.getBody());
        if (body.isPresent()) {
            var books = body.get().getResponse();
            log.info("Books received :: {}", books.size());
            return books;
        } else {
            throw new ResourceNotFoundException("No Books Found");
        }
    }

    public BookDto getBook(Long bookId) {
        log.info("Retrieving book :: {}", bookId);
        var responseEntity = bookServiceClient.getBook(bookId);
        log.info("Get Book response received with status :: {}", responseEntity.getStatusCodeValue());

        var body = Optional.ofNullable(responseEntity.getBody());
        if (body.isPresent()) {
            var book = body.get().getResponse();
            log.info("Book found :: {}", book);
            return book;
        } else {
            throw new ResourceNotFoundException("Book Not Found");
        }
    }

    public void addBook(BookDto book) {
        bookServiceClient.addBook(book);
        log.info("Book Added.");
    }

    public void deleteBook(Long bookId) {
        bookServiceClient.deleteBook(bookId);
        log.info("Book {} Deleted.", bookId);
    }

    public List<BookDto> getBooks(List<Long> bookIds) {
        log.info("Getting {} books with id [{}]", bookIds.size(), bookIds);
        var responseEntity = bookServiceClient.getBooks(bookIds);
        log.info("Book service response received with status :: {}", responseEntity.getStatusCodeValue());

        var body = Optional.ofNullable(responseEntity.getBody());
        if (body.isPresent()) {
            var books = body.get().getResponse();
            log.info("Books received :: {}", books.size());
            return books;
        } else {
            throw new ResourceNotFoundException("No Books Found");
        }
    }
}
