package in.hp.java.libraryservice.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserBookRecordIdentifier implements Serializable {

    private static final long serialVersionUID = -7444765544590236613L;

    private Long userId;

    private Long bookId;

}
