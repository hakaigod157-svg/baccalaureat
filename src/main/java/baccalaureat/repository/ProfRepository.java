package baccalaureat.repository;

import baccalaureat.model.Prof;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class ProfRepository {
    
    @PersistenceContext
    private EntityManager entityManager;
    
    public Prof save(Prof prof) {
        if (prof.getIdProf() == null) {
            entityManager.persist(prof);
            return prof;
        } else {
            return entityManager.merge(prof);
        }
    }
    
    public Prof findById(Integer id) {
        return entityManager.find(Prof.class, id);
    }
    
    public List<Prof> findAll() {
        return entityManager.createQuery("SELECT p FROM Prof p", Prof.class)
                .getResultList();
    }
    
    public Prof findByNom(String nom) {
        List<Prof> profs = entityManager
                .createQuery("SELECT p FROM Prof p WHERE p.nom = :nom", Prof.class)
                .setParameter("nom", nom)
                .getResultList();
        return profs.isEmpty() ? null : profs.get(0);
    }
    
    public void delete(Prof prof) {
        if (entityManager.contains(prof)) {
            entityManager.remove(prof);
        } else {
            entityManager.remove(entityManager.merge(prof));
        }
    }
    
    public void deleteById(Integer id) {
        Prof prof = findById(id);
        if (prof != null) {
            delete(prof);
        }
    }
}
