package ml.programmersoffice.spring5.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import ml.programmersoffice.spring5.entities.AlbumEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

@Repository
public interface AlbumRepository extends
 JpaRepository<AlbumEntity, Integer>, JpaSpecificationExecutor<AlbumEntity> {

    public Page<AlbumEntity> findAll(Specification<AlbumEntity> and, Pageable pageable);

    public static final String _query
    = "SELECT a.id, a.albumname, a.images, a.url"
    + " FROM albums a LEFT JOIN musics_albums ab"
    + "  ON a.albumname = ab.albumname"
    + " WHERE ab.title = :title";

    @Query(value = _query, nativeQuery = true)
    List<AlbumEntity> findByMusictitle(@Param("title") String title);

}
