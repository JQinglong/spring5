package ml.programmersoffice.spring5.services;

// import ml.programmersoffice.spring5.services.spec.MusicSpecifications.*;

import java.util.List;
import java.util.Arrays;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import ml.programmersoffice.spring5.entities.MusicEntity;
import ml.programmersoffice.spring5.entities.AlbumEntity;
// import ml.programmersoffice.spring5.entities.AlbumEntity_;
import ml.programmersoffice.spring5.repositories.AlbumRepository;
import ml.programmersoffice.spring5.repositories.MusicRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
// import org.springframework.data.domain.Pageable;
// import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
// import org.springframework.util.StringUtils;

@Service
@Transactional
public class AlbumService {

    private static final int PAGE_SIZE=300;

    @Autowired
    private AlbumRepository albumRepo;
    @Autowired
    private MusicRepository musicRepo;

    public List<AlbumEntity> getAll() {
        return albumRepo.findAll();
    }

    // public Page<AlbumEntity> search(int page, List<String> lyrics) {
    // public Page<AlbumEntity> search(int page, String lyrics) {
    //     return repo.findAll(Specification
    //         // .where(titleIn(lyrics))
    //         // .and(lyricsContains(lyrics))
    //         // .where(lyricsIn(lyrics))
    //         .where(lyricsContains(lyrics))
    //         // .or(titleIn(lyrics))
    //         ,PageRequest.of(page<=0?0:page, PAGE_SIZE)
    //         // ,PageRequest.of(page<=0?0:page, PAGE_SIZE, new Sort(
    //         //         Sort.Direction.ASC, "title"))
    //     );
    // }

    public List<AlbumEntity> findAll() {
        return albumRepo.findAll();
    }

    public AlbumEntity findOne(Integer id) {
        return albumRepo.findById(id).orElse(null);
    }

    public List<MusicEntity> findMusics(String albumname) {
        try {
            return musicRepo.findByAlbumname(albumname);
        } catch (Exception e) {
            throw(e);
        }

    }

    public AlbumEntity save(AlbumEntity player) {
        return albumRepo.save(player);
    }

    public void delete(Integer id) {
        albumRepo.deleteById(id);
    }


    // public Page<AlbumEntity> findAll(String lyricsQuery, Pageable pageable) {
    // public List<AlbumEntity> findAll(String lyricsQuery) {
    //         // クエリを複数キーワードに分割する
    //     final List<String> keywords = splitQuery(lyricsQuery);
    //     // 何もしないSpecificationを生成する。reduceの初期値として利用する
    //     // Specification.where()にnullを渡せば、何もしないSpecificationが生成される
    //     final Specification<AlbumEntity> zero = Specification.where((Specification<AlbumEntity>)null);
    //     // キーワードのリストをそれぞれSpecificationにマッピングして、andで結合する
    //     final Specification<AlbumEntity> spec = keywords
    //         .stream()
    //         .map(this::lyricsContains)
    //         .reduce(zero, Specification<AlbumEntity>::and);
    
    //         // return repo.findAll(spec, pageable);
    //         return repo.findAll(spec);
    //     }

    // private static Specification<AlbumEntity> lyricsContains(final String lyrics) {
    // private static Specification<AlbumEntity> lyricsContains(final String lyrics) {
    // private Specification<AlbumEntity> lyricsContains(String lyrics) {
    //     // return StringUtils.isEmpty(lyrics) ? null : new Specification<AlbumEntity>() {
    //     //     @Override
    //     //     public Predicate toPredicate(Root<AlbumEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
    //     //         return cb.like(root.get("lyrics"), "%" + lyrics + "%");
    //     //     }
    //     // };
    //     return new Specification<AlbumEntity>() {
    //         @Override
    //         public Predicate toPredicate(Root<AlbumEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
    //             return cb.like(root.get(AlbumEntity_.LYRICS), "%" + lyrics + "%");
    //         }
    //     };
    // }
    // private static Specification<AlbumEntity> lyricsIn(final List<String> lyrics) {
    //     return lyrics==null||lyrics.size()==0 ? null : new Specification<AlbumEntity>() {
    //         @Override
    //         public Predicate toPredicate(Root<AlbumEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
    //             return cb.in(root.get("lyrics")).value(lyrics);
    //         }
    //     };
    // }
    // private static Specification<AlbumEntity> titleIn(final List<String> lyrics) {
    //     return lyrics==null||lyrics.size()==0 ? null : new Specification<AlbumEntity>() {
    //         @Override
    //         public Predicate toPredicate(Root<AlbumEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
    //             return cb.in(root.get("lyrics")).value(lyrics);
    //         }
    //     };
    // }

    private List<String> splitQuery(String query) {
        final String space = " ";
        // 半角スペースと全角スペースの組み合わせのパターンを表す
        final String spacesPattern = "[\\s　]+";
        // 以上のパターンにマッチした部分を単一の半角スペースに変換する
        final String monoSpaceQuery = query.replaceAll(spacesPattern, space);
        // splitするとき、余分な空要素が生成されるのを防ぐため、先頭と末尾のスペースを削除する
        final String trimmedMonoSpaceQuery = monoSpaceQuery.trim();
        // 半角スペースでクエリをsplitする
        return Arrays.asList(trimmedMonoSpaceQuery.split("\\s"));
    }




}
