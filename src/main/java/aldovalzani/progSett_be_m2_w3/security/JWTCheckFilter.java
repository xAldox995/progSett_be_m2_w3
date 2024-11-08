package aldovalzani.progSett_be_m2_w3.security;

import aldovalzani.progSett_be_m2_w3.entities.Utente;
import aldovalzani.progSett_be_m2_w3.exceptions.UnauthorizedException;
import aldovalzani.progSett_be_m2_w3.services.UtenteServ;
import aldovalzani.progSett_be_m2_w3.tools.JWT;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JWTCheckFilter extends OncePerRequestFilter {

    @Autowired
    private JWT jwt;

    @Autowired
    private UtenteServ utenteServ;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //Sezione di AUTENTICAZINE
        String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer "))
            throw new UnauthorizedException("Inserire token nell'Authorization Header nel formato corretto!");
        String accessToken = authHeader.substring(7);
        jwt.verifyToken(accessToken);
        //Sezione di AUTORIZZAZIONE
        String utenteId = jwt.getIdFromToken(accessToken);
        Utente currentUtente = this.utenteServ.findUtenteById(Long.parseLong(utenteId));
        Authentication authentication = new UsernamePasswordAuthenticationToken(currentUtente, null, currentUtente.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        filterChain.doFilter(request, response);
    }

    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return new AntPathMatcher().match("/**", request.getServletPath());
    }
}
