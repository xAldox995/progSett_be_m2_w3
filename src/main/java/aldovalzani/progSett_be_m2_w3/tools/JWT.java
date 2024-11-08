package aldovalzani.progSett_be_m2_w3.tools;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JWT {
    @Value("${jwt.secret}")
    private String secret;

    
}
