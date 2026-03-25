package forage.model;

import javax.persistence.*;

@Entity
@Table(name = "TypeDevis")
public class TypeDevis {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idTypeDevis")
    private Integer idTypeDevis;

    @Column(name = "libelle", nullable = false, length = 50)
    private String libelle;

    public TypeDevis() {
    }

    public Integer getIdTypeDevis() {
        return idTypeDevis;
    }

    public void setIdTypeDevis(Integer idTypeDevis) {
        this.idTypeDevis = idTypeDevis;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }
}