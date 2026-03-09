package baccalaureat.repository;

import baccalaureat.model.Note;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class NoteRepository {
    
    @PersistenceContext
    private EntityManager entityManager;
    
    public Note save(Note note) {
        if (note.getIdNote() == null) {
            entityManager.persist(note);
            return note;
        } else {
            return entityManager.merge(note);
        }
    }
    
    public Note findById(Integer id) {
        return entityManager.find(Note.class, id);
    }
    
    public List<Note> findAll() {
        return entityManager.createQuery(
            "SELECT n FROM Note n LEFT JOIN FETCH n.candidat LEFT JOIN FETCH n.matiere LEFT JOIN FETCH n.prof", Note.class)
                .getResultList();
    }
    
    public List<Note> findByCandidat(Integer candidatId) {
        return entityManager.createQuery(
            "SELECT n FROM Note n WHERE n.candidat.idCandidat = :candidatId", Note.class)
                .setParameter("candidatId", candidatId)
                .getResultList();
    }
    
    public List<Note> findByMatiere(Integer matiereId) {
        return entityManager.createQuery(
            "SELECT n FROM Note n WHERE n.matiere.idMatiere = :matiereId", Note.class)
                .setParameter("matiereId", matiereId)
                .getResultList();
    }
    
    public List<Note> findByProf(Integer profId) {
        return entityManager.createQuery(
            "SELECT n FROM Note n WHERE n.prof.idProf = :profId", Note.class)
                .setParameter("profId", profId)
                .getResultList();
    }
    
    public List<Note> findBySerie(Integer serieId) {
        return entityManager.createQuery(
            "SELECT n FROM Note n WHERE n.candidat.serie.idSerie = :serieId", Note.class)
                .setParameter("serieId", serieId)
                .getResultList();
    }
    
    public List<Note> findByCandidatAndMatiere(Integer candidatId, Integer matiereId) {
        return entityManager.createQuery(
            "SELECT n FROM Note n WHERE n.candidat.idCandidat = :candidatId AND n.matiere.idMatiere = :matiereId", Note.class)
                .setParameter("candidatId", candidatId)
                .setParameter("matiereId", matiereId)
                .getResultList();
    }
    
    public long count() {
        return entityManager.createQuery("SELECT COUNT(n) FROM Note n", Long.class)
                .getSingleResult();
    }
    
    public void delete(Note note) {
        if (entityManager.contains(note)) {
            entityManager.remove(note);
        } else {
            entityManager.remove(entityManager.merge(note));
        }
    }
    
    public void deleteById(Integer id) {
        Note note = findById(id);
        if (note != null) {
            delete(note);
        }
    }
}
