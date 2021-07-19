package in.hp.java.libraryservice.exception;

public class ResourceNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 6409599354270555842L;

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
