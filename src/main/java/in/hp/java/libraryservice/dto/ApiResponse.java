package in.hp.java.libraryservice.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.ZoneId;

@Schema(name = "Library Api Response", description = "Library Api Generic Response")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse<T> {

    @Schema(name = "Generic Response")
    T response;

    @Schema(name = "Timestamp")
    @Builder.Default
    @JsonFormat(pattern = "dd-MM-yyyy hh:mm:ss")
    LocalDateTime timestamp = LocalDateTime.now(ZoneId.of(ZoneId.SHORT_IDS.get("IST")));
}
