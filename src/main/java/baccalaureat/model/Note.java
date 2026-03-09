package baccalaureat.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "Notes")
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idNote")
    private Integer idNote;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idCandidat", nullable = false)
    private Candidat candidat;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idMatiere", nullable = false)
    private Matiere matiere;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idProf", nullable = false)
    private Prof prof;

    @Column(name = "note", precision = 10, scale = 2)
    private Double valeurNote;

    @Column(name = "dateNote")
    private LocalDateTime dateNote;

    public Note() {
    }

    public Integer getIdNote() {
        return idNote;
    }

    public void setIdNote(Integer idNote) {
        this.idNote = idNote;
    }

    public Candidat getCandidat() {
        return candidat;
    }

    public void setCandidat(Candidat candidat) {
        this.candidat = candidat;
    }

    public Matiere getMatiere() {
        return matiere;
    }

    public void setMatiere(Matiere matiere) {
        this.matiere = matiere;
    }

    public Prof getProf() {
        return prof;
    }

    public void setProf(Prof prof) {
        this.prof = prof;
    }

    public Double getValeurNote() {
        return valeurNote;
    }

    public void setValeurNote(Double valeurNote) {
        this.valeurNote = valeurNote;
    }

    public LocalDateTime getDateNote() {
        return dateNote;
    }

    public void setDateNote(LocalDateTime dateNote) {
        this.dateNote = dateNote;
    }
}
