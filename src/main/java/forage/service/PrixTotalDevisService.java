package forage.service;

import forage.model.PrixTotalDevis;
import forage.repository.PrixTotalDevisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PrixTotalDevisService {

    @Autowired
    private PrixTotalDevisRepository prixTotalDevisRepository;

    public PrixTotalDevis savePrixTotalDevis(PrixTotalDevis prixTotalDevis) {
        return prixTotalDevisRepository.save(prixTotalDevis);
    }
}
