package in.hp.java.libraryservice.service;

import in.hp.java.libraryservice.repository.LibraryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class LibraryService {

    @Autowired
    private LibraryRepository libraryRepository;

    @Autowired
    private BookService bookService;

    @Autowired
    private UserService userService;

}
