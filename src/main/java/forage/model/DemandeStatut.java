package forage.model;
import java.time.*;
import javax.persistence.*;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "DemandeStatut")
public class DemandeStatut{

    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    @Column(name = "idDemandeStatut")
    private Integer idDemandeStatut;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idDemande" , nullable = false)
    private Demande demande;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idStatut" , nullable = false)
    private Statut statut;


    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    @Column(name = "date")
    private LocalDateTime date;

    public DemandeStatut() {
    }

    public Integer getIdDemandeStatut() {
        return idDemandeStatut;
    }

    public void setIdDemandeStatut(Integer idDemandeStatut) {
        this.idDemandeStatut = idDemandeStatut;
    }

    public Demande getDemande() {
        return demande;
    }

    public void setDemande(Demande demande) {
        this.demande = demande;
    }

    public Statut getStatut() {
        return statut;
    }

    public void setStatut(Statut statut) {
        this.statut = statut;
    }
}