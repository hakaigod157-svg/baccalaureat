package forage.service;

import forage.model.Lieu;
import forage.repository.LieuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class LieuService {

    @Autowired
    private LieuRepository lieuRepository;

    public Lieu saveLieu(Lieu lieu) {
        return lieuRepository.save(lieu);
    }

    public Lieu getLieuById(Integer id) {
        return lieuRepository.findById(id);
    }

    public List<Lieu> getAllLieus() {
        return lieuRepository.findAll();
    }

    public void deleteLieu(Lieu lieu) {
        lieuRepository.delete(lieu);
    }

    public void deleteLieuById(Integer id) {
        lieuRepository.deleteById(id);
    }
}
