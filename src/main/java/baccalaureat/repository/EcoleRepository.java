package baccalaureat.repository;

import baccalaureat.model.Ecole;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class EcoleRepository {
    
    @PersistenceContext
    private EntityManager entityManager;
    
    public Ecole save(Ecole ecole) {
        if (ecole.getIdEcole() == null) {
            entityManager.persist(ecole);
            return ecole;
        } else {
            return entityManager.merge(ecole);
        }
    }
    
    public Ecole findById(Integer id) {
        return entityManager.find(Ecole.class, id);
    }
    
    public List<Ecole> findAll() {
        return entityManager.createQuery("SELECT e FROM Ecole e", Ecole.class)
                .getResultList();
    }
    
    public void delete(Ecole ecole) {
        if (entityManager.contains(ecole)) {
            entityManager.remove(ecole);
        } else {
            entityManager.remove(entityManager.merge(ecole));
        }
    }
    
    public void deleteById(Integer id) {
        Ecole ecole = findById(id);
        if (ecole != null) {
            delete(ecole);
        }
    }
}
