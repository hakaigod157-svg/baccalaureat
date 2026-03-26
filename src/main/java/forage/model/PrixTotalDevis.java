package forage.model;

import javax.persistence.*;

@Entity
@Table(name = "PrixTotalDevis")
public class PrixTotalDevis {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idPrixTotalDevis")
    private Integer idPrixTotalDevis;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idDevis", nullable = false)
    private Devis devis;

    @Column(name = "montantTotal", precision = 10, scale = 2)
    private Double montantTotal;

    public PrixTotalDevis() {}

    public Integer getIdPrixTotalDevis() { return idPrixTotalDevis; }
    public void setIdPrixTotalDevis(Integer idPrixTotalDevis) { this.idPrixTotalDevis = idPrixTotalDevis; }

    public Devis getDevis() { return devis; }
    public void setDevis(Devis devis) { this.devis = devis; }

    public Double getMontantTotal() { return montantTotal; }
    public void setMontantTotal(Double montantTotal) { this.montantTotal = montantTotal; }
}
