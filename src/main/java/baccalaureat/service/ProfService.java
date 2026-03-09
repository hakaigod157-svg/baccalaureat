package baccalaureat.service;

import baccalaureat.model.Prof;
import baccalaureat.repository.ProfRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProfService {

    @Autowired
    private ProfRepository profRepository;

    public List<Prof> getAllProfs() {
        return profRepository.findAll();
    }

    public Prof getProfById(Integer id) {
        return profRepository.findById(id);
    }

    public Prof saveProf(Prof prof) {
        return profRepository.save(prof);
    }

    public void deleteProfById(Integer id) {
        profRepository.deleteById(id);
    }
}
