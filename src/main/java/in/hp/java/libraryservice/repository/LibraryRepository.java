package in.hp.java.libraryservice.repository;

import in.hp.java.libraryservice.entity.Library;
import in.hp.java.libraryservice.entity.UserBookRecordIdentifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LibraryRepository extends JpaRepository<Library, UserBookRecordIdentifier> {

    void deleteByRecordIdBookId(Long bookId);

    void deleteByRecordIdUserId(Long userId);

    List<Library> findByRecordIdUserId(Long userId);
}
