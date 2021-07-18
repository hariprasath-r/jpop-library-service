package in.hp.java.libraryservice.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "LIBRARY")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Library {

    @EmbeddedId
    private UserBookRecordIdentifier recordId;

}
