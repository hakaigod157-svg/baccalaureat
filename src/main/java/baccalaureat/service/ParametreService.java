package baccalaureat.service;

import baccalaureat.model.Parametre;
import baccalaureat.repository.ParametreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ParametreService {

    @Autowired
    private ParametreRepository parametreRepository;

    public List<Parametre> getAllParametres() {
        return parametreRepository.findAll();
    }

    public Parametre getParametreById(Integer id) {
        return parametreRepository.findById(id);
    }

    public Parametre saveParametre(Parametre parametre) {
        return parametreRepository.save(parametre);
    }

    public void deleteParametreById(Integer id) {
        parametreRepository.deleteById(id);
    }
}
