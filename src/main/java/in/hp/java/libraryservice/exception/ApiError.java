package in.hp.java.libraryservice.exception;

public class ApiError {

    private String message;
    private String details;

    public ApiError() {
    }

    public ApiError(String message, String details) {
        this.message = message;
        this.details = details;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    @Override
    public String toString() {
        final var sb = new StringBuilder("ApiException{");
        sb.append("message='").append(message).append('\'');
        sb.append(", details='").append(details).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
