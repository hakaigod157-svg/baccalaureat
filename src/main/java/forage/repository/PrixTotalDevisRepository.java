package forage.repository;

import forage.model.PrixTotalDevis;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class PrixTotalDevisRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public PrixTotalDevis save(PrixTotalDevis prixTotalDevis) {
        if (prixTotalDevis.getIdPrixTotalDevis() == null) {
            entityManager.persist(prixTotalDevis);
            return prixTotalDevis;
        } else {
            return entityManager.merge(prixTotalDevis);
        }
    }
}
