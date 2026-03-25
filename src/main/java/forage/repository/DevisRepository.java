package forage.repository;

import forage.model.Devis;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class DevisRepository {
    
    @PersistenceContext
    private EntityManager entityManager;
    
    public Devis save(Devis devis) {
        if (devis.getIdDevis() == null) {
            entityManager.persist(devis);
            return devis;
        } else {
            return entityManager.merge(devis);
        }
    }
    
    public Devis findById(Integer id) {
        return entityManager.find(Devis.class, id);
    }
    
    public List<Devis> findAll() {
        return entityManager.createQuery(
            "SELECT d FROM Devis d LEFT JOIN FETCH d.demande LEFT JOIN FETCH d.typeDevis LEFT JOIN FETCH d.statut", Devis.class)
            .getResultList();
    }
    
    public void delete(Devis devis) {
        if (entityManager.contains(devis)) {
            entityManager.remove(devis);
        } else {
            entityManager.remove(entityManager.merge(devis));
        }
    }
    
    public void deleteById(Integer id) {
        Devis d = findById(id);
        if (d != null) delete(d);
    }
}
