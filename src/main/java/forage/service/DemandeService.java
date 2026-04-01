package forage.service;

import forage.model.Demande;
import forage.repository.DemandeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import forage.model.DemandeStatut;
import forage.model.Statut;
// import forage.service.DemandeStatutService;
// import forage.service.StatutService;
import java.time.LocalDateTime;

@Service
@Transactional
public class DemandeService {

    @Autowired
    private DemandeRepository demandeRepository;

    @Autowired
    private StatutService statutService;

    @Autowired
    private DemandeStatutService demandeStatutService;

    public Demande saveDemande(Demande demande) {
        boolean vao2 = (demande.getIdDemande() == null);
        Demande savedDemande = demandeRepository.save(demande);
        
        if (vao2) {
            Statut statutCree = statutService.getStatutByLibelle("Cree");
            if (statutCree != null) {
                DemandeStatut ds = new DemandeStatut();
                ds.setDemande(savedDemande);
                ds.setStatut(statutCree);
                ds.setDateDemandeStatut(LocalDateTime.now());
                demandeStatutService.saveDemandeStatut(ds);
            }
        }
        return savedDemande;
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
