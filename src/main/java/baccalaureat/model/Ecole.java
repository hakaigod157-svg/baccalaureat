package baccalaureat.model;

import javax.persistence.*;

@Entity
@Table(name = "Ecole")
public class Ecole{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idEcole")
    private Integer idEcole;

    @Column(name = "nomEcole", length = 50)
    private String nomEcole;

    @Column(name = "adresse", length = 50)
    private String adresse;

    public Ecole() {
    }
    public Ecole(Integer idEcole, String nomEcole, String adresse) {
        this.idEcole = idEcole;
        this.nomEcole = nomEcole;
        this.adresse = adresse;
    }

    public Integer getIdEcole() {
        return idEcole;
    }

    public void setIdEcole(Integer idEcole) {
        this.idEcole = idEcole;
    }

    public String getNomEcole() {
        return nomEcole;
    }

    public void setNomEcole(String nomEcole) {
        this.nomEcole = nomEcole;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }
}
