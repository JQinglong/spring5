package ml.programmersoffice.spring5.repositories;

import java.util.List;

import ml.programmersoffice.spring5.entities.WordEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@Repository
public interface WordRepository extends JpaRepository<WordEntity, Integer> {

    public static final String _query = "SELECT *"
            + "  FROM words w"
            + "  WHERE w.title = :title";

    @Query(value = _query, nativeQuery = true)
    List<WordEntity> findByTitle(@Param("title") String title);

}
