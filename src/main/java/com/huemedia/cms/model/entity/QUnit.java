package com.huemedia.cms.model.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QUnit is a Querydsl query type for Unit
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QUnit extends EntityPathBase<Unit> {

    private static final long serialVersionUID = 1666803632;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QUnit unit = new QUnit("unit");

    public final StringPath createBy = createString("createBy");

    public final DateTimePath<java.util.Date> createDate = createDateTime("createDate", java.util.Date.class);

    public final QGroup group;

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath name = createString("name");

    public final SetPath<StaffGroup, QStaffGroup> staffGroups = this.<StaffGroup, QStaffGroup>createSet("staffGroups", StaffGroup.class, QStaffGroup.class, PathInits.DIRECT2);

    public final SetPath<TicketAssignment, QTicketAssignment> ticketAssignments = this.<TicketAssignment, QTicketAssignment>createSet("ticketAssignments", TicketAssignment.class, QTicketAssignment.class, PathInits.DIRECT2);

    public final StringPath updateBy = createString("updateBy");

    public final DateTimePath<java.util.Date> updateDate = createDateTime("updateDate", java.util.Date.class);

    public QUnit(String variable) {
        this(Unit.class, forVariable(variable), INITS);
    }

    @SuppressWarnings("all")
    public QUnit(Path<? extends Unit> path) {
        this((Class)path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QUnit(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QUnit(PathMetadata<?> metadata, PathInits inits) {
        this(Unit.class, metadata, inits);
    }

    public QUnit(Class<? extends Unit> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.group = inits.isInitialized("group") ? new QGroup(forProperty("group")) : null;
    }

}

