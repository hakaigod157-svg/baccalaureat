package forage.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "Devis")
public class Devis {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name ="idDevis")
    private Integer idDevis;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    @Column(name = "dateDevis")
    private LocalDateTime date;

    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name = "idDemande" , nullable = false )
    private Demande demande;

    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name = "idTypeDevis" , nullable = false )
    private TypeDevis typeDevis;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idStatut", nullable = false)
    private Statut statut;

    public Devis() {}

    public Integer getIdDevis() { return idDevis; }
    public void setIdDevis(Integer idDevis) { this.idDevis = idDevis; }
    
    public LocalDateTime getDate() { return date; }
    public void setDate(LocalDateTime date) { this.date = date; }
    
    public Demande getDemande() { return demande; }
    public void setDemande(Demande demande) { this.demande = demande; }
    
    public TypeDevis getTypeDevis() { return typeDevis; }
    public void setTypeDevis(TypeDevis typeDevis) { this.typeDevis = typeDevis; }
    
    public Statut getStatut() { return statut; }
    public void setStatut(Statut statut) { this.statut = statut; }
}
