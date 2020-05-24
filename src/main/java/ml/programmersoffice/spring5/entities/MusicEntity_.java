package ml.programmersoffice.spring5.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(MusicEntity.class)
public abstract class MusicEntity_ {

	public static volatile SingularAttribute<MusicEntity, Integer> id;
	public static volatile SingularAttribute<MusicEntity, String> title;
	public static volatile SingularAttribute<MusicEntity, String> lyrics;
	public static volatile SingularAttribute<MusicEntity, String> url;

	public static final String ID = "id";
	public static final String TITLE = "title";
	public static final String LYRICS = "lyrics";
	public static final String URL = "url";

}

