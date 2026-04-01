package forage.service;

import forage.model.DemandeStatut;
import forage.repository.DemandeStatutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class DemandeStatutService {

    @Autowired
    private DemandeStatutRepository demandeStatutRepository;

    public DemandeStatut saveDemandeStatut(DemandeStatut demandeStatut) {
        return demandeStatutRepository.save(demandeStatut);
    }

    public DemandeStatut getDemandeStatutById(Integer id) {
        return demandeStatutRepository.findById(id);
    }

    public List<DemandeStatut> getAllDemandeStatuts() {
        return demandeStatutRepository.findAll();
    }

    public void deleteDemandeStatut(DemandeStatut demandeStatut) {
        demandeStatutRepository.delete(demandeStatut);
    }

    public void deleteDemandeStatutById(Integer id) {
        demandeStatutRepository.deleteById(id);
    }

    public Long countDemandeStatuts() {
        return demandeStatutRepository.count();
    }

    public DemandeStatut getLastStatutByDemande(Integer id) {
        return demandeStatutRepository.lastStatut(id);
    }
}
