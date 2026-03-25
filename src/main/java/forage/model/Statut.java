package forage.model;

import javax.persistence.*;

@Entity
@Table(name="Statut")
public class Statut {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idStatut")
    private Integer idStatut;

    @Column(name = "libelle", nullable = false, length = 50)
    private String libelle;

    public Statut() {
    }

    public Integer getIdStatut() {
        return idStatut;
    }

    public void setIdStatut(Integer idStatut) {
        this.idStatut = idStatut;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }
}