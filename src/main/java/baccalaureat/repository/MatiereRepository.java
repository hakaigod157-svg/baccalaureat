package baccalaureat.repository;

import baccalaureat.model.Matiere;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class MatiereRepository {
    
    @PersistenceContext
    private EntityManager entityManager;
    
    public Matiere save(Matiere matiere) {
        if (matiere.getIdMatiere() == null) {
            entityManager.persist(matiere);
            return matiere;
        } else {
            return entityManager.merge(matiere);
        }
    }
    
    public Matiere findById(Integer id) {
        return entityManager.find(Matiere.class, id);
    }
    
    public List<Matiere> findAll() {
        return entityManager.createQuery("SELECT m FROM Matiere m", Matiere.class)
                .getResultList();
    }
    
    public void delete(Matiere matiere) {
        if (entityManager.contains(matiere)) {
            entityManager.remove(matiere);
        } else {
            entityManager.remove(entityManager.merge(matiere));
        }
    }
    
    public void deleteById(Integer id) {
        Matiere matiere = findById(id);
        if (matiere != null) {
            delete(matiere);
        }
    }
}
