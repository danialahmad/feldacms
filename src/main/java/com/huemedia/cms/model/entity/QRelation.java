package com.huemedia.cms.model.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QRelation is a Querydsl query type for Relation
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QRelation extends EntityPathBase<Relation> {

    private static final long serialVersionUID = -1265921496;

    public static final QRelation relation = new QRelation("relation");

    public final StringPath createBy = createString("createBy");

    public final DateTimePath<java.util.Date> createDate = createDateTime("createDate", java.util.Date.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath name = createString("name");

    public final SetPath<Profile, QProfile> profiles = this.<Profile, QProfile>createSet("profiles", Profile.class, QProfile.class, PathInits.DIRECT2);

    public final StringPath updateBy = createString("updateBy");

    public final DateTimePath<java.util.Date> updateDate = createDateTime("updateDate", java.util.Date.class);

    public QRelation(String variable) {
        super(Relation.class, forVariable(variable));
    }

    @SuppressWarnings("all")
    public QRelation(Path<? extends Relation> path) {
        super((Class)path.getType(), path.getMetadata());
    }

    public QRelation(PathMetadata<?> metadata) {
        super(Relation.class, metadata);
    }

}

