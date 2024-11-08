package aldovalzani.progSett_be_m2_w3.services;

import aldovalzani.progSett_be_m2_w3.repositories.UtenteRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UtenteServ {
    @Autowired
    private UtenteRepo utenteRepo;

    
}
