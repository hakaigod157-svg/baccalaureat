package baccalaureat.model;

import javax.persistence.*;

@Entity
@Table(name = "Resolution")
public class Resolution {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idResolution")
    private Integer idResolution;

    @Column(name = "operation", length = 50)
    private String operation;

    public Resolution() {
    }

    public Integer getIdResolution() {
        return idResolution;
    }

    public void setIdResolution(Integer idResolution) {
        this.idResolution = idResolution;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }
}
