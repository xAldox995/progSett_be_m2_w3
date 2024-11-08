package aldovalzani.progSett_be_m2_w3.entities;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "eventi")
@Getter
@Setter
@NoArgsConstructor
public class Evento {
    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    private long id;
    private String nome;
    @Column(name = "data_evento")
    private LocalDate dataEvento;
    private String luogo;
    @Column(name = "posti_disponibili")
    private int postiDisponibili;
    @ManyToOne
    @JoinColumn(name = "id_organizzatore", nullable = false)
    private Utente organizzatore;
    @ManyToMany
    @JoinTable(
            name = "evento_partecipanti",
            joinColumns = @JoinColumn(name = "evento_id"),
            inverseJoinColumns = @JoinColumn(name = "utente_id")
    )
    private List<Utente> partecipanti = new ArrayList<>();


    public Evento(LocalDate dataEvento, String luogo, String nome, Utente organizzatore, int postiDisponibili) {
        this.dataEvento = dataEvento;
        this.luogo = luogo;
        this.nome = nome;
        this.organizzatore = organizzatore;
        this.postiDisponibili = postiDisponibili;
    }
}
