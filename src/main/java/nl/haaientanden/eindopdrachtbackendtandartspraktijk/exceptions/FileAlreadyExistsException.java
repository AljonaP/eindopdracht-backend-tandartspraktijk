package nl.haaientanden.eindopdrachtbackendtandartspraktijk.exceptions;

public class FileAlreadyExistsException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public FileAlreadyExistsException() {
        super();
    }

    public FileAlreadyExistsException(String message) {
        super(message);
    }
}
