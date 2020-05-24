package ml.programmersoffice.spring5.services;

// import ml.programmersoffice.spring5.services.spec.MusicSpecifications.*;

import java.util.List;
import java.util.Arrays;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import ml.programmersoffice.spring5.entities.MusicEntity;
import ml.programmersoffice.spring5.entities.MusicEntity_;
import ml.programmersoffice.spring5.entities.WordEntity;
import ml.programmersoffice.spring5.entities.HskEntity;
import ml.programmersoffice.spring5.repositories.AlbumRepository;
import ml.programmersoffice.spring5.repositories.HskRepository;
import ml.programmersoffice.spring5.repositories.MusicRepository;
import ml.programmersoffice.spring5.repositories.WordRepository;
import ml.programmersoffice.spring5.repositories.HskRepository;
import ml.programmersoffice.spring5.entities.AlbumEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
// import org.springframework.data.domain.Example;
// import org.springframework.data.domain.Pageable;
// import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
// import org.springframework.util.StringUtils;

@Service
@Transactional
public class MusicService {

    private static final int PAGE_SIZE=300;

    @Autowired
    private MusicRepository musicRepo;
    @Autowired
    private AlbumRepository albumRepo;
    @Autowired
    private WordRepository wordsRepo;
    @Autowired
    private HskRepository hskRepo;

    public List<MusicEntity> getAll() {
        return musicRepo.findAll();
    }

    // public Page<MusicEntity> search(int page, List<String> lyrics) {
    public Page<MusicEntity> search(int page, String lyrics) {
        return musicRepo.findAll(Specification
            // .where(titleIn(lyrics))
            // .and(lyricsContains(lyrics))
            // .where(lyricsIn(lyrics))
            .where(lyricsContains(lyrics))
            // .or(titleIn(lyrics))
            ,PageRequest.of(page<=0?0:page, PAGE_SIZE)
            // ,PageRequest.of(page<=0?0:page, PAGE_SIZE, new Sort(
            //         Sort.Direction.ASC, "title"))
        );
    }

    public List<MusicEntity> findAll() {
        return musicRepo.findAll();
    }

    public List<AlbumEntity> findAlbums(String title) {
        return albumRepo.findByMusictitle(title);
    }
    
    public List<WordEntity> findWords(String title) {
        return wordsRepo.findByTitle(title);
    }
    
    public List<HskEntity> findHsk(String title) {
        return hskRepo.findByTitle(title);
    }
    
    public MusicEntity findOne(Integer id) {
        return musicRepo.findById(id).orElse(null);
    }
    public MusicEntity save(MusicEntity player) {
        return musicRepo.save(player);
    }

    public void delete(Integer id) {
        musicRepo.deleteById(id);
    }


    // public Page<MusicEntity> findAll(String lyricsQuery, Pageable pageable) {
    public List<MusicEntity> findAll(String lyricsQuery) {
            // クエリを複数キーワードに分割する
        final List<String> keywords = splitQuery(lyricsQuery);
        // 何もしないSpecificationを生成する。reduceの初期値として利用する
        // Specification.where()にnullを渡せば、何もしないSpecificationが生成される
        final Specification<MusicEntity> zero = Specification.where((Specification<MusicEntity>)null);
        // キーワードのリストをそれぞれSpecificationにマッピングして、andで結合する
        final Specification<MusicEntity> spec = keywords
            .stream()
            .map(this::lyricsContains)
            .reduce(zero, Specification<MusicEntity>::and);
    
            // return repo.findAll(spec, pageable);
            return musicRepo.findAll(spec);
        }

    // private static Specification<MusicEntity> lyricsContains(final String lyrics) {
    // private static Specification<MusicEntity> lyricsContains(final String lyrics) {
    private Specification<MusicEntity> lyricsContains(String lyrics) {
        // return StringUtils.isEmpty(lyrics) ? null : new Specification<MusicEntity>() {
        //     @Override
        //     public Predicate toPredicate(Root<MusicEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        //         return cb.like(root.get("lyrics"), "%" + lyrics + "%");
        //     }
        // };
        return new Specification<MusicEntity>() {
            @Override
            public Predicate toPredicate(Root<MusicEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                return cb.like(root.get(MusicEntity_.LYRICS), "%" + lyrics + "%");
            }
        };
    }
    // private static Specification<MusicEntity> lyricsIn(final List<String> lyrics) {
    //     return lyrics==null||lyrics.size()==0 ? null : new Specification<MusicEntity>() {
    //         @Override
    //         public Predicate toPredicate(Root<MusicEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
    //             return cb.in(root.get("lyrics")).value(lyrics);
    //         }
    //     };
    // }
    // private static Specification<MusicEntity> titleIn(final List<String> lyrics) {
    //     return lyrics==null||lyrics.size()==0 ? null : new Specification<MusicEntity>() {
    //         @Override
    //         public Predicate toPredicate(Root<MusicEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
    //             return cb.in(root.get("lyrics")).value(lyrics);
    //         }
    //     };
    // }

    public List<String> splitQuery(String query) {
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
