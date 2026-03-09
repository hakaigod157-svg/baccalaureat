package baccalaureat.model;

import javax.persistence.*;

@Entity
@Table(name = "Matieres")
public class Matiere {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idMatiere")
    private Integer idMatiere;

    @Column(name = "nom", length = 50)
    private String nom;

    @Column(name = "coeff", precision = 10, scale = 2)
    private Double coeff;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idSerie", nullable = false)
    private Serie serie;

    public Matiere() {
    }

    public Integer getIdMatiere() {
        return idMatiere;
    }

    public void setIdMatiere(Integer idMatiere) {
        this.idMatiere = idMatiere;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Double getCoeff() {
        return coeff;
    }

    public void setCoeff(Double coeff) {
        this.coeff = coeff;
    }

    public Serie getSerie() {
        return serie;
    }

    public void setSerie(Serie serie) {
        this.serie = serie;
    }
}
