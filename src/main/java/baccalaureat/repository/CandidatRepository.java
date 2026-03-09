package baccalaureat.repository;

import baccalaureat.model.Candidat;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class CandidatRepository {
    
    @PersistenceContext
    private EntityManager entityManager;
    
    public Candidat save(Candidat candidat) {
        if (candidat.getIdCandidat() == null) {
            entityManager.persist(candidat);
            return candidat;
        } else {
            return entityManager.merge(candidat);
        }
    }
    
    public Candidat findById(Integer id) {
        return entityManager.find(Candidat.class, id);
    }
    
    public List<Candidat> findAll() {
        return entityManager.createQuery(
            "SELECT c FROM Candidat c LEFT JOIN FETCH c.ecole LEFT JOIN FETCH c.serie", Candidat.class)
                .getResultList();
    }
    
    public List<Candidat> findByEcole(Integer ecoleId) {
        return entityManager.createQuery("SELECT c FROM Candidat c WHERE c.ecole.idEcole = :ecoleId", Candidat.class)
                .setParameter("ecoleId", ecoleId)
                .getResultList();
    }
    
    public List<Candidat> findBySerie(Integer serieId) {
        return entityManager.createQuery("SELECT c FROM Candidat c WHERE c.serie.idSerie = :serieId", Candidat.class)
                .setParameter("serieId", serieId)
                .getResultList();
    }
    
    public void delete(Candidat candidat) {
        if (entityManager.contains(candidat)) {
            entityManager.remove(candidat);
        } else {
            entityManager.remove(entityManager.merge(candidat));
        }
    }
    
    public void deleteById(Integer id) {
        Candidat candidat = findById(id);
        if (candidat != null) {
            delete(candidat);
        }
    }
}
