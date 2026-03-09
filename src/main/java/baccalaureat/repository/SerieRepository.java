package baccalaureat.repository;

import baccalaureat.model.Serie;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class SerieRepository {
    
    @PersistenceContext
    private EntityManager entityManager;
    
    public Serie save(Serie serie) {
        if (serie.getIdSerie() == null) {
            entityManager.persist(serie);
            return serie;
        } else {
            return entityManager.merge(serie);
        }
    }
    
    public Serie findById(Integer id) {
        return entityManager.find(Serie.class, id);
    }
    
    public List<Serie> findAll() {
        return entityManager.createQuery("SELECT s FROM Serie s", Serie.class)
                .getResultList();
    }
    
    public void delete(Serie serie) {
        if (entityManager.contains(serie)) {
            entityManager.remove(serie);
        } else {
            entityManager.remove(entityManager.merge(serie));
        }
    }
    
    public void deleteById(Integer id) {
        Serie serie = findById(id);
        if (serie != null) {
            delete(serie);
        }
    }
}
