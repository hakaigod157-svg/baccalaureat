package forage.service;

import forage.model.DetailsDevis;
import forage.repository.DetailsDevisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class DetailsDevisService {

    @Autowired
    private DetailsDevisRepository detailsDevisRepository;

    public DetailsDevis saveDetailsDevis(DetailsDevis detailsDevis) {
        return detailsDevisRepository.save(detailsDevis);
    }

    public DetailsDevis getDetailsDevisById(Integer id) {
        return detailsDevisRepository.findById(id);
    }

    public List<DetailsDevis> getAllDetailsDevis() {
        return detailsDevisRepository.findAll();
    }

    public void deleteDetailsDevis(DetailsDevis detailsDevis) {
        detailsDevisRepository.delete(detailsDevis);
    }

    public void deleteDetailsDevisById(Integer id) {
        detailsDevisRepository.deleteById(id);
    }
}
