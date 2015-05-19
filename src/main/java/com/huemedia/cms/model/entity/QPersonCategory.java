package com.huemedia.cms.model.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QPersonCategory is a Querydsl query type for PersonCategory
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QPersonCategory extends EntityPathBase<PersonCategory> {

    private static final long serialVersionUID = -1245290849;

    public static final QPersonCategory personCategory = new QPersonCategory("personCategory");

    public final StringPath createBy = createString("createBy");

    public final DateTimePath<java.util.Date> createDate = createDateTime("createDate", java.util.Date.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath name = createString("name");

    public final SetPath<Profile, QProfile> profiles = this.<Profile, QProfile>createSet("profiles", Profile.class, QProfile.class, PathInits.DIRECT2);

    public final StringPath updateBy = createString("updateBy");

    public final DateTimePath<java.util.Date> updateDate = createDateTime("updateDate", java.util.Date.class);

    public QPersonCategory(String variable) {
        super(PersonCategory.class, forVariable(variable));
    }

    @SuppressWarnings("all")
    public QPersonCategory(Path<? extends PersonCategory> path) {
        super((Class)path.getType(), path.getMetadata());
    }

    public QPersonCategory(PathMetadata<?> metadata) {
        super(PersonCategory.class, metadata);
    }

}

