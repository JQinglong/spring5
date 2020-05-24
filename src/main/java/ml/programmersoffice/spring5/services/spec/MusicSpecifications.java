package ml.programmersoffice.spring5.services.spec;

import java.util.List;
import java.util.Arrays;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import ml.programmersoffice.spring5.entities.MusicEntity;

public class MusicSpecifications {
    public static Specification<MusicEntity> lyricsContains(final String lyrics) {
        return StringUtils.isEmpty(lyrics) ? null : new Specification<MusicEntity>() {
            @Override
            public Predicate toPredicate(Root<MusicEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                return cb.like(root.get("lyrics"), "%" + lyrics + "%");
            }
        };
    }
    public static Specification<MusicEntity> lyricsIn(final List<String> lyrics) {
        return lyrics==null||lyrics.size()==0 ? null : new Specification<MusicEntity>() {
            @Override
            public Predicate toPredicate(Root<MusicEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                return cb.in(root.get("lyrics")).value(lyrics);
            }
        };
    }
    public static Specification<MusicEntity> titleIn(final List<String> lyrics) {
        return lyrics==null||lyrics.size()==0 ? null : new Specification<MusicEntity>() {
            @Override
            public Predicate toPredicate(Root<MusicEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                return cb.in(root.get("lyrics")).value(lyrics);
            }
        };
    }

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
