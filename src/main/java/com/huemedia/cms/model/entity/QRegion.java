package com.huemedia.cms.model.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QRegion is a Querydsl query type for Region
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QRegion extends EntityPathBase<Region> {

    private static final long serialVersionUID = -318776800;

    public static final QRegion region = new QRegion("region");

    public final StringPath createBy = createString("createBy");

    public final DateTimePath<java.util.Date> createDate = createDateTime("createDate", java.util.Date.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath name = createString("name");

    public final SetPath<Plan, QPlan> plans = this.<Plan, QPlan>createSet("plans", Plan.class, QPlan.class, PathInits.DIRECT2);

    public final SetPath<Profile, QProfile> profiles = this.<Profile, QProfile>createSet("profiles", Profile.class, QProfile.class, PathInits.DIRECT2);

    public final StringPath updateBy = createString("updateBy");

    public final DateTimePath<java.util.Date> updateDate = createDateTime("updateDate", java.util.Date.class);

    public QRegion(String variable) {
        super(Region.class, forVariable(variable));
    }

    @SuppressWarnings("all")
    public QRegion(Path<? extends Region> path) {
        super((Class)path.getType(), path.getMetadata());
    }

    public QRegion(PathMetadata<?> metadata) {
        super(Region.class, metadata);
    }

}

