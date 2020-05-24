package ml.programmersoffice.spring5.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import ml.programmersoffice.spring5.entities.MusicEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

@Repository
public interface MusicRepository extends
 JpaRepository<MusicEntity, Integer>, JpaSpecificationExecutor<MusicEntity> {

    public Page<MusicEntity> findAll(Specification<MusicEntity> and, Pageable pageable);

    public static final String _query
        = "SELECT m.id, m.title, m.lyrics, m.url"
        + "  FROM musics m LEFT JOIN musics_albums ab"
        + "   ON m.title = ab.title"
        + "  WHERE ab.albumname = :albumname";
    
    @Query(value = _query, nativeQuery = true)
    List<MusicEntity> findByAlbumname(@Param("albumname") String albumname);

}
