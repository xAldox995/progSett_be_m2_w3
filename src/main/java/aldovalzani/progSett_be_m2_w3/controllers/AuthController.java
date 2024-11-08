package aldovalzani.progSett_be_m2_w3.controllers;

import aldovalzani.progSett_be_m2_w3.dto.NewUtenteDTO;
import aldovalzani.progSett_be_m2_w3.dto.UtenteLogResponse;
import aldovalzani.progSett_be_m2_w3.dto.UtenteLoginDTO;
import aldovalzani.progSett_be_m2_w3.entities.Utente;
import aldovalzani.progSett_be_m2_w3.exceptions.BadRequestException;
import aldovalzani.progSett_be_m2_w3.services.AuthServ;
import aldovalzani.progSett_be_m2_w3.services.UtenteServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;
//    http://localhost:3001/auth

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthServ authServ;
    @Autowired
    private UtenteServ utenteServ;

    @PostMapping("/login")
    public UtenteLogResponse login(@RequestBody UtenteLoginDTO body) {
        return new UtenteLogResponse(this.authServ.checkUtenteCredentialAndGenToken(body));
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public Utente saveUtente(@RequestBody NewUtenteDTO body, BindingResult validationResult) {
        if (validationResult.hasErrors()) {
            String mes = validationResult.getAllErrors().stream().map(objectError -> objectError.getDefaultMessage())
                    .collect(Collectors.joining(", "));
            throw new BadRequestException("Ci sono errori nel payload! " + mes);
        }
        return this.utenteServ.saveUtente(body);
    }
}
