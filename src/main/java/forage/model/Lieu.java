package forage.model;

import javax.persistence.*;

@Entity
@Table(name="Lieu")
public class Lieu {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idLieu")
    private Integer idLieu;

    @Column(name = "localisation", nullable = false, length = 50)
    private String localisation;

    @Column(name = "district", nullable = false, length = 50)
    private String district;

    public Lieu() {
    }

    public Integer getIdLieu() {
        return idLieu;
    }

    public void setIdLieu(Integer idLieu) {
        this.idLieu = idLieu;
    }

    public String getLocalisation() {
        return localisation;
    }

    public void setLocalisation(String localisation) {
        this.localisation = localisation;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }
}
