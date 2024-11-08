package aldovalzani.progSett_be_m2_w3.entities;

import aldovalzani.progSett_be_m2_w3.entities.enums.Ruolo;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "utenti")
@Getter
@Setter
@NoArgsConstructor
public class Utente {
    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    private long id;
    private String nome;
    private String cognome;
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    private Ruolo ruolo;

    public Utente(String cognome, String email, String nome, String password) {
        this.cognome = cognome;
        this.email = email;
        this.nome = nome;
        this.password = password;
        this.ruolo = Ruolo.CLIENTE;
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(this.ruolo.name()));
    }
}
