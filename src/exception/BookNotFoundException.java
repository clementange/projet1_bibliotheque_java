package exception;

public class BookNotFoundException extends RuntimeException {

    public BookNotFoundException(int id){
        super("Aucun livre trouvé avec l'id : "+ id);
    }
}
