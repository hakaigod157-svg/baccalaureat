package forage.service;

import forage.model.Statut;
import forage.repository.StatutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class StatutService {

    @Autowired
    private StatutRepository statutRepository;

    public Statut saveStatut(Statut statut) {
        return statutRepository.save(statut);
    }

    public Statut getStatutById(Integer id) {
        return statutRepository.findById(id);
    }

    public List<Statut> getAllStatuts() {
        return statutRepository.findAll();
    }

    public void deleteStatut(Statut statut) {
        statutRepository.delete(statut);
    }

    public void deleteStatutById(Integer id) {
        statutRepository.deleteById(id);
    }
}
