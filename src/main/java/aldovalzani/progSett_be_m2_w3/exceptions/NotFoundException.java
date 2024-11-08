package aldovalzani.progSett_be_m2_w3.exceptions;

public class NotFoundException extends RuntimeException {
    public NotFoundException(long id) {
        super("Il record con id " + id + " non è stato trovato!");
    }

    public NotFoundException(String email) {
        super("Il record con email: " + email + " non è stato trovato!");
    }

}
