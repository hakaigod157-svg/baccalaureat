package baccalaureat.service;

import baccalaureat.model.Serie;
import baccalaureat.repository.SerieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SerieService {

    @Autowired
    private SerieRepository serieRepository;

    public List<Serie> getAllSeries() {
        return serieRepository.findAll();
    }

    public Serie getSerieById(Integer id) {
        return serieRepository.findById(id);
    }

    public Serie saveSerie(Serie serie) {
        return serieRepository.save(serie);
    }

    public void deleteSerieById(Integer id) {
        serieRepository.deleteById(id);
    }
}
