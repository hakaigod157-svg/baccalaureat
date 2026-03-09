package baccalaureat.repository;

import baccalaureat.model.Resolution;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class ResolutionRepository {
    
    @PersistenceContext
    private EntityManager entityManager;
    
    public Resolution save(Resolution resolution) {
        if (resolution.getIdResolution() == null) {
            entityManager.persist(resolution);
            return resolution;
        } else {
            return entityManager.merge(resolution);
        }
    }
    
    public Resolution findById(Integer id) {
        return entityManager.find(Resolution.class, id);
    }
    
    public List<Resolution> findAll() {
        return entityManager.createQuery("SELECT r FROM Resolution r", Resolution.class)
                .getResultList();
    }
    
    public void delete(Resolution resolution) {
        if (entityManager.contains(resolution)) {
            entityManager.remove(resolution);
        } else {
            entityManager.remove(entityManager.merge(resolution));
        }
    }
    
    public void deleteById(Integer id) {
        Resolution resolution = findById(id);
        if (resolution != null) {
            delete(resolution);
        }
    }
}
