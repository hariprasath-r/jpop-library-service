package in.hp.java.libraryservice.repository;

import in.hp.java.libraryservice.entity.Library;
import in.hp.java.libraryservice.entity.UserBookRecordIdentifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LibraryRepository extends JpaRepository<Library, UserBookRecordIdentifier> {
}
