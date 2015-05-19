package com.huemedia.cms.model.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QState is a Querydsl query type for State
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QState extends EntityPathBase<State> {

    private static final long serialVersionUID = 129629157;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QState state = new QState("state");

    public final QCountry country;

    public final StringPath flagActive = createString("flagActive");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath name = createString("name");

    public final SetPath<Plan, QPlan> plans = this.<Plan, QPlan>createSet("plans", Plan.class, QPlan.class, PathInits.DIRECT2);

    public final SetPath<Profile, QProfile> profiles = this.<Profile, QProfile>createSet("profiles", Profile.class, QProfile.class, PathInits.DIRECT2);

    public final StringPath timezone = createString("timezone");

    public QState(String variable) {
        this(State.class, forVariable(variable), INITS);
    }

    @SuppressWarnings("all")
    public QState(Path<? extends State> path) {
        this((Class)path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QState(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QState(PathMetadata<?> metadata, PathInits inits) {
        this(State.class, metadata, inits);
    }

    public QState(Class<? extends State> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.country = inits.isInitialized("country") ? new QCountry(forProperty("country")) : null;
    }

}

