package forage.service;

import forage.model.Devis;
import forage.repository.DevisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class DevisService {

    @Autowired
    private DevisRepository devisRepository;

    public Devis saveDevis(Devis devis) {
        return devisRepository.save(devis);
    }

    public Devis getDevisById(Integer id) {
        return devisRepository.findById(id);
    }

    public List<Devis> getAllDevis() {
        return devisRepository.findAll();
    }

    public void deleteDevis(Devis devis) {
        devisRepository.delete(devis);
    }

    public void deleteDevisById(Integer id) {
        devisRepository.deleteById(id);
    }

    public Long countDevis() {
        return devisRepository.count();
    }

    public List<Devis> getRecentDevis(int max) {
        return devisRepository.findRecent(max);
    }
}
