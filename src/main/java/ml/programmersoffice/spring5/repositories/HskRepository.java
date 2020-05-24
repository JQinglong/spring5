package ml.programmersoffice.spring5.repositories;

import java.util.List;

import ml.programmersoffice.spring5.entities.HskEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@Repository
public interface HskRepository extends JpaRepository<HskEntity, Integer> {

    public static final String _query = "SELECT *"
    + "  FROM hsk"
    + "  where LOCATE(hsk.hanzi,"
    + "  (select lyrics from musics where title = :title)) > 0";

    @Query(value = _query, nativeQuery = true)
    List<HskEntity> findByTitle(@Param("title") String title);

}
