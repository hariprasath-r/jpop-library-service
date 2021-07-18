package in.hp.java.libraryservice.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.ZoneId;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LibraryApiResponse<T> {

    T response;

    @Builder.Default
    @JsonFormat(pattern = "dd-MM-yyyy hh:mm:ss")
    LocalDateTime timestamp = LocalDateTime.now(ZoneId.of(ZoneId.SHORT_IDS.get("IST")));
}
