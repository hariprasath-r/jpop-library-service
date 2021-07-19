package in.hp.java.libraryservice.service;

import in.hp.java.libraryservice.dto.UserDto;
import in.hp.java.libraryservice.entity.Library;
import in.hp.java.libraryservice.entity.UserBookRecordIdentifier;
import in.hp.java.libraryservice.repository.LibraryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

@Service
@Slf4j
public class LibraryService {

    @Autowired
    private LibraryRepository libraryRepository;

    @Autowired
    private BookService bookService;

    @Autowired
    private UserService userService;

    @Transactional
    public void deleteBook(Long bookId) {
        log.info("Deleting book :: {}", bookId);
        bookService.deleteBook(bookId);

        log.info("Removing book {} for all users.", bookId);
        libraryRepository.deleteByRecordIdBookId(bookId);
        log.info("Removed book {} for all users.", bookId);
    }

    @Transactional
    public void deleteUser(Long userId) {
        log.info("Deleting User :: {}", userId);
        userService.deleteUser(userId);

        log.info("Releasing all books from user {}", userId);
        libraryRepository.deleteByRecordIdUserId(userId);
        log.info("Released all books for user {}", userId);
    }

    public void issueBookForUser(Long bookId, Long userId) {
        checkUserAndBookExists(bookId, userId);

        log.info("Issuing book {}, for user {}", bookId, userId);
        var libraryRecord = new UserBookRecordIdentifier(userId, bookId);
        libraryRepository.save(new Library(libraryRecord));
        log.info("Issued book {}, for user {}", bookId, userId);
    }

    public void removeBookForUser(Long bookId, Long userId) {
        checkUserAndBookExists(bookId, userId);

        log.info("Removing book {}, for user {}", bookId, userId);
        var libraryRecord = new UserBookRecordIdentifier(userId, bookId);
        libraryRepository.delete(new Library(libraryRecord));
        log.info("Removed book {}, for user {}", bookId, userId);
    }

    public UserDto getUserBooks(Long userId) {
        var user = userService.getUser(userId);

        var booksOwned = libraryRepository.findByRecordIdUserId(userId);
        log.info("Books owned by user {} is {}", userId, booksOwned.size());
        var bookIds = booksOwned.stream()
                .map(row -> row.getRecordId().getBookId())
                .collect(Collectors.toList());

        if (!bookIds.isEmpty()) {
            user.setBooks(bookService.getBooks(bookIds));
        }

        return user;
    }

    private void checkUserAndBookExists(Long bookId, Long userId) {
        checkBookExists(bookId);
        checkUserExists(userId);
    }

    private void checkBookExists(Long bookId) {
        log.info("Checking if book {} exists", bookId);
        bookService.getBook(bookId);
    }

    private void checkUserExists(Long userId) {
        log.info("Checking if user {} exists", userId);
        userService.getUser(userId);
    }
}
