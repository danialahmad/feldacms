package com.huemedia.cms.model.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QStaffGroup is a Querydsl query type for StaffGroup
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QStaffGroup extends EntityPathBase<StaffGroup> {

    private static final long serialVersionUID = -598979413;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QStaffGroup staffGroup = new QStaffGroup("staffGroup");

    public final QGroup group;

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final QProfile profile;

    public final QUnit unit;

    public QStaffGroup(String variable) {
        this(StaffGroup.class, forVariable(variable), INITS);
    }

    @SuppressWarnings("all")
    public QStaffGroup(Path<? extends StaffGroup> path) {
        this((Class)path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QStaffGroup(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QStaffGroup(PathMetadata<?> metadata, PathInits inits) {
        this(StaffGroup.class, metadata, inits);
    }

    public QStaffGroup(Class<? extends StaffGroup> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.group = inits.isInitialized("group") ? new QGroup(forProperty("group")) : null;
        this.profile = inits.isInitialized("profile") ? new QProfile(forProperty("profile"), inits.get("profile")) : null;
        this.unit = inits.isInitialized("unit") ? new QUnit(forProperty("unit"), inits.get("unit")) : null;
    }

}

