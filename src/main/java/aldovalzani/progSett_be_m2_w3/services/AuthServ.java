package aldovalzani.progSett_be_m2_w3.services;

import aldovalzani.progSett_be_m2_w3.dto.UtenteLoginDTO;
import aldovalzani.progSett_be_m2_w3.entities.Utente;
import aldovalzani.progSett_be_m2_w3.exceptions.UnauthorizedException;
import aldovalzani.progSett_be_m2_w3.tools.JWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServ {
    @Autowired
    private UtenteServ utenteServ;
    @Autowired
    private JWT jwt;
    @Autowired
    private PasswordEncoder bcrypt;

    public String checkUtenteCredentialAndGenToken(UtenteLoginDTO body) {
        Utente utTrovato = this.utenteServ.findUtenteByEmail(body.email());

        if (bcrypt.matches(body.password(), utTrovato.getPassword())) {
            String accessToken = jwt.createToken(utTrovato);
            return accessToken;
        } else {
            throw new UnauthorizedException("Credenziali Errate");
        }
    }

}
