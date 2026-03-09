package baccalaureat.model;

import javax.persistence.*;

@Entity
@Table(name = "serie")
public class Serie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idSerie")
    private Integer idSerie;

    @Column(name = "libelle", length = 50)
    private String libelle;

    public Serie() {
    }

    public Integer getIdSerie() {
        return idSerie;
    }

    public void setIdSerie(Integer idSerie) {
        this.idSerie = idSerie;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }
}
