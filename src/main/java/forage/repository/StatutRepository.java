package forage.repository;

import forage.model.Statut;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class StatutRepository {
    
    @PersistenceContext
    private EntityManager entityManager;
    
    public Statut save(Statut statut) {
        if (statut.getIdStatut() == null) {
            entityManager.persist(statut);
            return statut;
        } else {
            return entityManager.merge(statut);
        }
    }
    
    public Statut findById(Integer id) {
        return entityManager.find(Statut.class, id);
    }
    
    public List<Statut> findAll() {
        return entityManager.createQuery("SELECT s FROM Statut s", Statut.class).getResultList();
    }
    
    public void delete(Statut statut) {
        if (entityManager.contains(statut)) {
            entityManager.remove(statut);
        } else {
            entityManager.remove(entityManager.merge(statut));
        }
    }
    
    public void deleteById(Integer id) {
        Statut s = findById(id);
        if (s != null) delete(s);
    }
}
