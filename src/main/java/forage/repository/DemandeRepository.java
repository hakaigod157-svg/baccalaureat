package forage.repository;

import forage.model.Demande;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class DemandeRepository {
    
    @PersistenceContext
    private EntityManager entityManager;
    
    public Demande save(Demande demande) {
        if (demande.getIdDemande() == null) {
            entityManager.persist(demande);
            return demande;
        } else {
            return entityManager.merge(demande);
        }
    }
    
    public Demande findById(Integer id) {
        return entityManager.find(Demande.class, id);
    }
    
    public List<Demande> findAll() {
        return entityManager.createQuery(
            "SELECT d FROM Demande d LEFT JOIN FETCH d.client LEFT JOIN FETCH d.lieu", Demande.class)
            .getResultList();
    }
    
    public void delete(Demande demande) {
        if (entityManager.contains(demande)) {
            entityManager.remove(demande);
        } else {
            entityManager.remove(entityManager.merge(demande));
        }
    }
    
    public void deleteById(Integer id) {
        Demande d = findById(id);
        if (d != null) delete(d);
    }

    public Long count() {
        return entityManager.createQuery("SELECT COUNT(d) FROM Demande d", Long.class).getSingleResult();
    }

    public List<Demande> findRecent(int max) {
        return entityManager.createQuery(
            "SELECT d FROM Demande d LEFT JOIN FETCH d.client LEFT JOIN FETCH d.lieu ORDER BY d.dateDemande DESC", Demande.class)
            .setMaxResults(max)
            .getResultList();
    }
}
