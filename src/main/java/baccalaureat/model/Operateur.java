package baccalaureat.model;

import javax.persistence.*;

@Entity
@Table(name = "Operateur")
public class Operateur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idOperateur")
    private Integer idOperateur;

    @Column(name = "operation", length = 50)
    private String operation;

    public Operateur() {
    }

    public Integer getIdOperateur() {
        return idOperateur;
    }

    public void setIdOperateur(Integer idOperateur) {
        this.idOperateur = idOperateur;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }
}
