package forage.service;

import forage.model.Demande;
import forage.repository.DemandeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class DemandeService {

    @Autowired
    private DemandeRepository demandeRepository;

    public Demande saveDemande(Demande demande) {
        return demandeRepository.save(demande);
    }

    public Demande getDemandeById(Integer id) {
        return demandeRepository.findById(id);
    }

    public List<Demande> getAllDemandes() {
        return demandeRepository.findAll();
    }

    public void deleteDemande(Demande demande) {
        demandeRepository.delete(demande);
    }

    public void deleteDemandeById(Integer id) {
        demandeRepository.deleteById(id);
    }

    public Long countDemandes() {
        return demandeRepository.count();
    }

    public List<Demande> getRecentDemandes(int max) {
        return demandeRepository.findRecent(max);
    }
}
