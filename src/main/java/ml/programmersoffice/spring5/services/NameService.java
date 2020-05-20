package ml.programmersoffice.spring5.services;

import java.util.List;

import ml.programmersoffice.spring5.entities.NameEntity;
import ml.programmersoffice.spring5.repositories.NameRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NameService {
    @Autowired
    private NameRepository repo;

    public List<NameEntity> getAll() {
        return repo.findAll();
    }
}
