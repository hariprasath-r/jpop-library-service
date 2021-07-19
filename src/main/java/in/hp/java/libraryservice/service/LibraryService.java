package in.hp.java.libraryservice.service;

import in.hp.java.libraryservice.dto.BookDto;
import in.hp.java.libraryservice.entity.Library;
import in.hp.java.libraryservice.entity.UserBookRecordIdentifier;
import in.hp.java.libraryservice.repository.LibraryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;
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

    public void deleteBook(Long bookId) {
        CompletableFuture
                .runAsync(() -> libraryRepository.deleteByRecordIdBookId(bookId))
                .thenRun(() -> bookService.deleteBook(bookId))
                .join();
    }

    public void deleteUser(Long userId) {
        CompletableFuture
                .runAsync(() -> libraryRepository.deleteByRecordIdUserId(userId))
                .thenRun(() -> userService.deleteUser(userId))
                .join();
    }

    public void addBookForUser(Long bookId, Long userId) {
        var libraryRecord = new UserBookRecordIdentifier(userId, bookId);
        libraryRepository.save(new Library(libraryRecord));
    }

    public void removeBookForUser(Long bookId, Long userId) {
        var libraryRecord = new UserBookRecordIdentifier(userId, bookId);
        libraryRepository.delete(new Library(libraryRecord));
    }

    public List<BookDto> getUserBooks(Long userId) {
        var records = libraryRepository.findByRecordIdUserId(userId);
        var bookIds = records.stream()
                .map(record -> record.getRecordId().getBookId())
                .collect(Collectors.toList());

        // TODO: call book api passing list of bookId
        return Collections.emptyList();
    }
}
