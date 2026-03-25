package forage.repository;

import forage.model.Lieu;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class LieuRepository {
    
    @PersistenceContext
    private EntityManager entityManager;
    
    public Lieu save(Lieu lieu) {
        if (lieu.getIdLieu() == null) {
            entityManager.persist(lieu);
            return lieu;
        } else {
            return entityManager.merge(lieu);
        }
    }
    
    public Lieu findById(Integer id) {
        return entityManager.find(Lieu.class, id);
    }
    
    public List<Lieu> findAll() {
        return entityManager.createQuery("SELECT l FROM Lieu l", Lieu.class).getResultList();
    }
    
    public void delete(Lieu lieu) {
        if (entityManager.contains(lieu)) {
            entityManager.remove(lieu);
        } else {
            entityManager.remove(entityManager.merge(lieu));
        }
    }
    
    public void deleteById(Integer id) {
        Lieu l = findById(id);
        if (l != null) delete(l);
    }
}
