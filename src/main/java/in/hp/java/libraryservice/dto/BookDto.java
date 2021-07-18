package in.hp.java.libraryservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Schema(name = "Book Data")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookDto {

    private Long id;

    @NotEmpty(message = "Book name should not be empty")
    private String bookName;

    private String description;

    private String genre;

    @NotEmpty(message = "Author should not be empty")
    private String author;
}
