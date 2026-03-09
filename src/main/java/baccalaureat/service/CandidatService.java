package baccalaureat.service;

import baccalaureat.model.Candidat;
import baccalaureat.repository.CandidatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CandidatService {

    @Autowired
    private CandidatRepository candidatRepository;

    public List<Candidat> getAllCandidats() {
        return candidatRepository.findAll();
    }

    public Candidat getCandidatById(Integer id) {
        return candidatRepository.findById(id);
    }

    public Candidat saveCandidat(Candidat candidat) {
        return candidatRepository.save(candidat);
    }

    public void deleteCandidatById(Integer id) {
        candidatRepository.deleteById(id);
    }

    public List<Candidat> getCandidatsByEcole(Integer ecoleId) {
        return candidatRepository.findByEcole(ecoleId);
    }

    public List<Candidat> getCandidatsBySerie(Integer serieId) {
        return candidatRepository.findBySerie(serieId);
    }
}
