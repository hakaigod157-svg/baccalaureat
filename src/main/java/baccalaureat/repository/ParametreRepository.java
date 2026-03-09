package baccalaureat.repository;

import baccalaureat.model.Parametre;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class ParametreRepository {
    
    @PersistenceContext
    private EntityManager entityManager;
    
    public Parametre save(Parametre parametre) {
        if (parametre.getIdParametre() == null) {
            entityManager.persist(parametre);
            return parametre;
        } else {
            return entityManager.merge(parametre);
        }
    }
    
    public Parametre findById(Integer id) {
        return entityManager.find(Parametre.class, id);
    }
    
    public List<Parametre> findAll() {
        return entityManager.createQuery(
            "SELECT p FROM Parametre p LEFT JOIN FETCH p.matiere LEFT JOIN FETCH p.operateur LEFT JOIN FETCH p.resolution", Parametre.class)
                .getResultList();
    }
    
    public void delete(Parametre parametre) {
        if (entityManager.contains(parametre)) {
            entityManager.remove(parametre);
        } else {
            entityManager.remove(entityManager.merge(parametre));
        }
    }
    
    public void deleteById(Integer id) {
        Parametre parametre = findById(id);
        if (parametre != null) {
            delete(parametre);
        }
    }
}
