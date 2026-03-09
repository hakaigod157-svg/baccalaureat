package baccalaureat.model;

import javax.persistence.*;

@Entity
@Table(name = "Candidat")
public class Candidat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idCandidat")
    private Integer idCandidat;

    @Column(name = "nom", nullable = false, length = 50)
    private String nom;

    @Column(name = "prenom", nullable = false, length = 50)
    private String prenom;

    @Column(name = "matricule", length = 50)
    private String matricule;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idEcole", nullable = false)
    private Ecole ecole;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idSerie", nullable = false)
    private Serie serie;

    public Candidat() {
    }

    public Integer getIdCandidat() {
        return idCandidat;
    }

    public void setIdCandidat(Integer idCandidat) {
        this.idCandidat = idCandidat;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public Ecole getEcole() {
        return ecole;
    }

    public void setEcole(Ecole ecole) {
        this.ecole = ecole;
    }

    public Serie getSerie() {
        return serie;
    }

    public void setSerie(Serie serie) {
        this.serie = serie;
    }
}
