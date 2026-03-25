package forage.service;

import forage.model.TypeDevis;
import forage.repository.TypeDevisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TypeDevisService {

    @Autowired
    private TypeDevisRepository typeDevisRepository;

    public TypeDevis saveTypeDevis(TypeDevis typeDevis) {
        return typeDevisRepository.save(typeDevis);
    }

    public TypeDevis getTypeDevisById(Integer id) {
        return typeDevisRepository.findById(id);
    }

    public List<TypeDevis> getAllTypeDevis() {
        return typeDevisRepository.findAll();
    }

    public void deleteTypeDevis(TypeDevis typeDevis) {
        typeDevisRepository.delete(typeDevis);
    }

    public void deleteTypeDevisById(Integer id) {
        typeDevisRepository.deleteById(id);
    }
}
