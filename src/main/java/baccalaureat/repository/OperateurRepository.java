package baccalaureat.repository;

import baccalaureat.model.Operateur;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class OperateurRepository {
    
    @PersistenceContext
    private EntityManager entityManager;
    
    public Operateur save(Operateur operateur) {
        if (operateur.getIdOperateur() == null) {
            entityManager.persist(operateur);
            return operateur;
        } else {
            return entityManager.merge(operateur);
        }
    }
    
    public Operateur findById(Integer id) {
        return entityManager.find(Operateur.class, id);
    }
    
    public List<Operateur> findAll() {
        return entityManager.createQuery("SELECT o FROM Operateur o", Operateur.class)
                .getResultList();
    }
    
    public void delete(Operateur operateur) {
        if (entityManager.contains(operateur)) {
            entityManager.remove(operateur);
        } else {
            entityManager.remove(entityManager.merge(operateur));
        }
    }
    
    public void deleteById(Integer id) {
        Operateur operateur = findById(id);
        if (operateur != null) {
            delete(operateur);
        }
    }
}
