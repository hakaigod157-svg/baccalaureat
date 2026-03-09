package baccalaureat.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "Parametres")
public class Parametre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idParametre")
    private Integer idParametre;

    @Column(name = "diffNotes", length = 50)
    private String diffNotes;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idMatiere", nullable = false)
    private Matiere matiere;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idOperateur", nullable = false)
    private Operateur operateur;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idResolution", nullable = false)
    private Resolution resolution;

    @Column(name = "dateAjout")
    private LocalDateTime dateAjout;

    public Parametre() {
    }

    public Integer getIdParametre() {
        return idParametre;
    }

    public void setIdParametre(Integer idParametre) {
        this.idParametre = idParametre;
    }

    public String getDiffNotes() {
        return diffNotes;
    }

    public void setDiffNotes(String diffNotes) {
        this.diffNotes = diffNotes;
    }

    public Matiere getMatiere() {
        return matiere;
    }

    public void setMatiere(Matiere matiere) {
        this.matiere = matiere;
    }

    public Operateur getOperateur() {
        return operateur;
    }

    public void setOperateur(Operateur operateur) {
        this.operateur = operateur;
    }

    public Resolution getResolution() {
        return resolution;
    }

    public void setResolution(Resolution resolution) {
        this.resolution = resolution;
    }

    public LocalDateTime getDateAjout() {
        return dateAjout;
    }

    public void setDateAjout(LocalDateTime dateAjout) {
        this.dateAjout = dateAjout;
    }
}
