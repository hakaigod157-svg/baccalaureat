package forage.repository;
import javax.persistence.*;

import forage.model.Client;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ClientRepository {
    
    @PersistenceContext
    private EntityManager entityManager;
    
    public Client save(Client client) {
        if (client.getIdClient() == null) {
            entityManager.persist(client);
            return client;
        } else {
            return entityManager.merge(client);
        }
    }
    
    public Client findById(Integer id) {
        return entityManager.find(Client.class, id);
    }
    
    public List<Client> findAll() {
        return entityManager.createQuery("SELECT c FROM Client c", Client.class).getResultList();
    }
    
    public void delete(Client client) {
        if (entityManager.contains(client)) {
            entityManager.remove(client);
        } else {
            entityManager.remove(entityManager.merge(client));
        }
    }
    
    public void deleteById(Integer id) {
        Client c = findById(id);
        if (c != null) delete(c);
    }
}
