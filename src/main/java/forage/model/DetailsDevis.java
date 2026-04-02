package forage.model;

import javax.persistence.*;

@Entity
@Table(name = "DetailDevis")
public class DetailsDevis {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idDetailDevis")
    private Integer idDetailsDevis;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idDevis", nullable = false)
    private Devis devis;

    @Column(name = "libelle", length = 50)
    private String libelle;

    @Column(name = "prixUnitaire", precision = 10, scale = 2)
    private Double prixUnitaire;

    @Column(name = "quantite")
    private Integer quantite;

    public DetailsDevis() {
    }

    public Integer getIdDetailsDevis() {
        return idDetailsDevis;
    }

    public void setIdDetailsDevis(Integer idDetailsDevis) {
        this.idDetailsDevis = idDetailsDevis;
    }

    public Devis getDevis() {
        return devis;
    }

    public void setDevis(Devis devis) {
        this.devis = devis;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public Double getPrixUnitaire() {
        return prixUnitaire;
    }

    public void setPrixUnitaire(Double prixUnitaire) {
        this.prixUnitaire = prixUnitaire;
    }

    public Integer getQuantite() {
        return quantite;
    }

    public void setQuantite(Integer quantite) {
        this.quantite = quantite;
    }
}