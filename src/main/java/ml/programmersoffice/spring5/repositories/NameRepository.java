package ml.programmersoffice.spring5.repositories;

import ml.programmersoffice.spring5.entities.NameEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NameRepository extends JpaRepository<NameEntity, Integer> {

}
