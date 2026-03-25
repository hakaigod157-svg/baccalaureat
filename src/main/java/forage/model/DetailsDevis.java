package forage.model;

import javax.persistence.*;
// import forage.model.*;

@Entity
@Table(name="DetailsDevis")
public class DetailsDevis {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idDetailsDevis")
    private Integer idDetailsDevis;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idDevis" , nullable = false)
    private Devis devis;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idLieu" , nullable = false)
    private Lieu lieu;

    @Column(name = "montant", precision = 10 , scale=2)
    private Double montant;

    @Column(name = "libelle", length = 50)
    private String libelle;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idStatut" , nullable = false)
    private Statut statut;

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

    public Lieu getLieu() {
        return lieu;
    }

    public void setLieu(Lieu lieu) {
        this.lieu = lieu;
    }

    public Double getMontant() {
        return montant;
    }

    public void setMontant(Double montant) {
        this.montant = montant;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public Statut getStatut() {
        return statut;
    }

    public void setStatut(Statut statut) {
        this.statut = statut;
    }
}