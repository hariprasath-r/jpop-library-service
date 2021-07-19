package in.hp.java.libraryservice.exception;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import feign.FeignException;
import in.hp.java.libraryservice.dto.ApiResponse;
import in.hp.java.libraryservice.utils.JsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Objects;

@RestController
@RestControllerAdvice
public class ExceptionHandlerAdvice extends ResponseEntityExceptionHandler {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private ApiResponse<ApiError> generateApiResponse(String message, String description) {
        return ApiResponse.<ApiError>builder()
                .response(new ApiError(message, description))
                .timestamp(LocalDateTime.now(ZoneId.of(ZoneId.SHORT_IDS.get("IST"))))
                .build();
    }

    @ExceptionHandler({ResourceNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public final ResponseEntity<ApiResponse<ApiError>> handleResourceNotFoundException(
            ResourceNotFoundException ex,
            WebRequest request
    ) {
        log.error("ResourceNotFoundException: {}", ex.getMessage());
        var apiResponse = generateApiResponse(
                ex.getMessage(), request.getDescription(false));

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiResponse);
    }

    @ExceptionHandler({FeignException.class, RestTemplateResponseException.class})
    public final ResponseEntity<Object> handleFeignException(Exception exception, WebRequest request) {
        HttpStatus status;
        String body;
        if (exception instanceof FeignException) {
            var ex = (FeignException) exception;
            status = HttpStatus.resolve(ex.status());
            status = Objects.nonNull(status) ? status : HttpStatus.INTERNAL_SERVER_ERROR;
            body = ex.contentUTF8();
        } else {
            var ex = (RestTemplateResponseException) exception;
            status = ex.getStatusCode();
            body = ex.getBody();
        }
        ResponseEntity.BodyBuilder responseEntity = ResponseEntity.status(status);
        ApiResponse<ApiError> responseException;
        try {
            responseException = JsonUtils.getMapper().readValue(
                    body,
                    new TypeReference<ApiResponse<ApiError>>() {
                    }
            );
            log.error("Exception: handleHttpClientErrorException [{}]", responseException);
            return responseEntity.body(responseException);
        } catch (JsonProcessingException ex) {
            responseException = generateApiResponse(exception.getMessage(), request.getDescription(false));
            log.error("Exception: handleHttpClientErrorException [{}]", responseException);
            return responseEntity.body(responseException);
        }
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public final ResponseEntity<ApiResponse<ApiError>> handleException(Exception ex) {
        log.error("Exception Occurred: {}", ex.getMessage());
        var apiResponse = generateApiResponse(
                ex.getMessage(), ex.getLocalizedMessage());

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(apiResponse);
    }

}
