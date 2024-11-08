package aldovalzani.progSett_be_m2_w3.repositories;

import aldovalzani.progSett_be_m2_w3.entities.Utente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UtenteRepo extends JpaRepository<Utente, Long> {
    Optional<Utente> findByEmail(String email);
}
