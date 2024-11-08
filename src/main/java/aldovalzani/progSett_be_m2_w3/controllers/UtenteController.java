package aldovalzani.progSett_be_m2_w3.controllers;

import aldovalzani.progSett_be_m2_w3.entities.Utente;
import aldovalzani.progSett_be_m2_w3.services.UtenteServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

//    http://localhost:3001/utenti

@RestController
@RequestMapping("/utenti")
public class UtenteController {
    @Autowired
    private UtenteServ utenteServ;

    @GetMapping
    public Page<Utente> findAllUtenti(@RequestParam(defaultValue = "0") int page,
                                      @RequestParam(defaultValue = "10") int size) {
        return this.utenteServ.findAllUtenti(page, size);
    }

    @GetMapping("/me")
    public Utente getProfile(@AuthenticationPrincipal Utente currentAuthenticatedUtente) {
        return currentAuthenticatedUtente;
    }

    @GetMapping("/{utenteId}")
    public Utente findById(@PathVariable long utenteId) {
        return this.utenteServ.findUtenteById(utenteId);
    }
}
