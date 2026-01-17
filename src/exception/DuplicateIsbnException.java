package exception;

public class DuplicateIsbnException extends RuntimeException {

    public DuplicateIsbnException(String isbn){
        super("Un livre avec l'ISBN "+ isbn + " existe déjà.");
    }
}
