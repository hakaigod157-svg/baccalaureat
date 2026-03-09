package baccalaureat.service;

import baccalaureat.model.Resolution;
import baccalaureat.repository.ResolutionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ResolutionService {

    @Autowired
    private ResolutionRepository resolutionRepository;

    public List<Resolution> getAllResolutions() {
        return resolutionRepository.findAll();
    }

    public Resolution getResolutionById(Integer id) {
        return resolutionRepository.findById(id);
    }

    public Resolution saveResolution(Resolution resolution) {
        return resolutionRepository.save(resolution);
    }

    public void deleteResolutionById(Integer id) {
        resolutionRepository.deleteById(id);
    }
}
