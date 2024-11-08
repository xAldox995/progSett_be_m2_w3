package aldovalzani.progSett_be_m2_w3.services;

import aldovalzani.progSett_be_m2_w3.dto.NewUtenteDTO;
import aldovalzani.progSett_be_m2_w3.entities.Utente;
import aldovalzani.progSett_be_m2_w3.exceptions.BadRequestException;
import aldovalzani.progSett_be_m2_w3.exceptions.NotFoundException;
import aldovalzani.progSett_be_m2_w3.repositories.UtenteRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UtenteServ {
    @Autowired
    private UtenteRepo utenteRepo;
    @Autowired
    private PasswordEncoder bcrypt;

    public Utente saveUtente(NewUtenteDTO body) {

        this.utenteRepo.findByEmail(body.email()).ifPresent(

                utente -> {
                    throw new BadRequestException("Email " + body.email() + " già in uso!");
                }
        );

        Utente newUtente = new Utente(body.cognome(), body.email(), body.nome(), bcrypt.encode(body.password()));

        Utente saveUtente = this.utenteRepo.save(newUtente);

        return saveUtente;
    }

    public Page<Utente> findAllUtenti(int page, int size) {
        if (size > 40) size = 40;
        Pageable pageable = PageRequest.of(page, size);
        return this.utenteRepo.findAll(pageable);
    }

    public Utente findUtenteById(long utenteId) {
        return this.utenteRepo.findById(utenteId).orElseThrow(() -> new NotFoundException(utenteId));
    }

    public Utente findUtenteByEmail(String email) {
        return this.utenteRepo.findByEmail(email).orElseThrow(() -> new NotFoundException("L'utente con email " + email + " non è stato trovato"));
    }


    public Utente findUtenteByIdAndUp(long utenteId, NewUtenteDTO body) {
        Utente found = this.findUtenteById(utenteId);

        if (!found.getEmail().equals(body.email())) {
            this.utenteRepo.findByEmail(body.email()).ifPresent(
                    user -> {
                        throw new BadRequestException("Email " + body.email() + " già in uso!");
                    }
            );
        }

        found.setNome(body.nome());
        found.setCognome(body.cognome());
        found.setEmail(body.email());
        found.setPassword(body.password());

        return this.utenteRepo.save(found);
    }

    public void findUtenteByIdAndDel(long utenteId) {
        Utente found = this.findUtenteById(utenteId);
        this.utenteRepo.delete(found);
    }

}
