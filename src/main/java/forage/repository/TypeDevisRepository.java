package forage.repository;

import forage.model.TypeDevis;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class TypeDevisRepository {
    
    @PersistenceContext
    private EntityManager entityManager;
    
    public TypeDevis save(TypeDevis typeDevis) {
        if (typeDevis.getIdTypeDevis() == null) {
            entityManager.persist(typeDevis);
            return typeDevis;
        } else {
            return entityManager.merge(typeDevis);
        }
    }
    
    public TypeDevis findById(Integer id) {
        return entityManager.find(TypeDevis.class, id);
    }
    
    public List<TypeDevis> findAll() {
        return entityManager.createQuery("SELECT t FROM TypeDevis t", TypeDevis.class).getResultList();
    }
    
    public void delete(TypeDevis typeDevis) {
        if (entityManager.contains(typeDevis)) {
            entityManager.remove(typeDevis);
        } else {
            entityManager.remove(entityManager.merge(typeDevis));
        }
    }
    
    public void deleteById(Integer id) {
        TypeDevis t = findById(id);
        if (t != null) delete(t);
    }
}
