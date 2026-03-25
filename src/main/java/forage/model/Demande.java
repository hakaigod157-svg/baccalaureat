package forage.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="Demande")
public class Demande {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idDemande")
    private Integer idDemande;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idClient", nullable = false)
    private Client client;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idLieu", nullable = false)
    private Lieu lieu;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    @Column(name = "dateDemande")
    private LocalDateTime dateDemande;

    public Demande() {}

    public Integer getIdDemande() { return idDemande; }
    public void setIdDemande(Integer idDemande) { this.idDemande = idDemande; }

    public Client getClient() { return client; }
    public void setClient(Client client) { this.client = client; }

    public Lieu getLieu() { return lieu; }
    public void setLieu(Lieu lieu) { this.lieu = lieu; }

    public LocalDateTime getDateDemande() { return dateDemande; }
    public void setDateDemande(LocalDateTime dateDemande) { this.dateDemande = dateDemande; }
}
