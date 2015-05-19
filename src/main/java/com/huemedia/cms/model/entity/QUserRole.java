package com.huemedia.cms.model.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QUserRole is a Querydsl query type for UserRole
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QUserRole extends EntityPathBase<UserRole> {

    private static final long serialVersionUID = -978019571;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QUserRole userRole = new QUserRole("userRole");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath roleId = createString("roleId");

    public final QUser user;

    public QUserRole(String variable) {
        this(UserRole.class, forVariable(variable), INITS);
    }

    @SuppressWarnings("all")
    public QUserRole(Path<? extends UserRole> path) {
        this((Class)path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QUserRole(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QUserRole(PathMetadata<?> metadata, PathInits inits) {
        this(UserRole.class, metadata, inits);
    }

    public QUserRole(Class<? extends UserRole> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.user = inits.isInitialized("user") ? new QUser(forProperty("user"), inits.get("user")) : null;
    }

}

