package forage.repository;
import forage.model.DetailsDevis;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class DetailsDevisRepository {
    
    @PersistenceContext
    private EntityManager entityManager;
    
    public DetailsDevis save(DetailsDevis detailsDevis) {
        if (detailsDevis.getIdDetailsDevis() == null) {
            entityManager.persist(detailsDevis);
            return detailsDevis;
        } else {
            return entityManager.merge(detailsDevis);
        }
    }
    
    public DetailsDevis findById(Integer id) {
        return entityManager.find(DetailsDevis.class, id);
    }
    
    public List<DetailsDevis> findAll() {
        return entityManager.createQuery(
            "SELECT d FROM DetailsDevis d LEFT JOIN FETCH d.devis LEFT JOIN FETCH d.lieu LEFT JOIN FETCH d.statut", DetailsDevis.class)
            .getResultList();
    }
    
    public void delete(DetailsDevis detailsDevis) {
        if (entityManager.contains(detailsDevis)) {
            entityManager.remove(detailsDevis);
        } else {
            entityManager.remove(entityManager.merge(detailsDevis));
        }
    }
    
    public void deleteById(Integer id) {
        DetailsDevis d = findById(id);
        if (d != null) delete(d);
    }
}
