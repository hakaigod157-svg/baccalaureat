package forage.repository;

import forage.model.DemandeStatut;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class DemandeStatutRepository {
    
    @PersistenceContext
    private EntityManager entityManager;
    
    public DemandeStatut save(DemandeStatut demandeStatut) {
        if (demandeStatut.getIdDemandeStatut() == null) {
            entityManager.persist(demandeStatut);
            return demandeStatut;
        } else {
            return entityManager.merge(demandeStatut);
        }
    }
    
    public DemandeStatut findById(Integer id) {
        return entityManager.find(DemandeStatut.class, id);
    }
    
    public List<DemandeStatut> findAll() {
        return entityManager.createQuery(
            "SELECT d FROM DemandeStatut d LEFT JOIN FETCH d.demande LEFT JOIN FETCH d.statut", DemandeStatut.class)
            .getResultList();
    }
    
    public void delete(DemandeStatut demandeStatut) {
        if (entityManager.contains(demandeStatut)) {
            entityManager.remove(demandeStatut);
        } else {
            entityManager.remove(entityManager.merge(demandeStatut));
        }
    }
    
    public void deleteById(Integer id) {
        DemandeStatut d = findById(id);
        if (d != null) delete(d);
    }

    public DemandeStatut lastStatut(Integer idDemande) {
        return entityManager.createQuery(
            "SELECT ds FROM DemandeStatut ds WHERE ds.idDemande = :idDemande ORDER BY ds.dateDemandeStatut DESC", DemandeStatut.class)
            .setParameter("idDemande", idDemande)
            .getSingleResult();
    }
}
