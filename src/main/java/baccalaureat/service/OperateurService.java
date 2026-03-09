package baccalaureat.service;

import baccalaureat.model.Operateur;
import baccalaureat.repository.OperateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class OperateurService {

    @Autowired
    private OperateurRepository operateurRepository;

    public List<Operateur> getAllOperateurs() {
        return operateurRepository.findAll();
    }

    public Operateur getOperateurById(Integer id) {
        return operateurRepository.findById(id);
    }

    public Operateur saveOperateur(Operateur operateur) {
        return operateurRepository.save(operateur);
    }

    public void deleteOperateurById(Integer id) {
        operateurRepository.deleteById(id);
    }
}
