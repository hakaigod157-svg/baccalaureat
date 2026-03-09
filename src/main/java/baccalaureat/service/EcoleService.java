package baccalaureat.service;

import baccalaureat.model.Ecole;
import baccalaureat.repository.EcoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class EcoleService {

    @Autowired
    private EcoleRepository ecoleRepository;

    public List<Ecole> getAllEcoles() {
        return ecoleRepository.findAll();
    }

    public Ecole getEcoleById(Integer id) {
        return ecoleRepository.findById(id);
    }

    public Ecole saveEcole(Ecole ecole) {
        return ecoleRepository.save(ecole);
    }

    public void deleteEcoleById(Integer id) {
        ecoleRepository.deleteById(id);
    }
}
