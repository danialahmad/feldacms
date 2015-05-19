package com.huemedia.cms.model.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QTicketAssignment is a Querydsl query type for TicketAssignment
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QTicketAssignment extends EntityPathBase<TicketAssignment> {

    private static final long serialVersionUID = -1147725019;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QTicketAssignment ticketAssignment = new QTicketAssignment("ticketAssignment");

    public final DateTimePath<java.util.Date> assignDate = createDateTime("assignDate", java.util.Date.class);

    public final NumberPath<Integer> assigneeId = createNumber("assigneeId", Integer.class);

    public final StringPath caseInterval = createString("caseInterval");

    public final StringPath createBy = createString("createBy");

    public final DateTimePath<java.util.Date> createDate = createDateTime("createDate", java.util.Date.class);

    public final NumberPath<Integer> duration = createNumber("duration", Integer.class);

    public final QGroup group;

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final DateTimePath<java.util.Date> lastStatusUpdate = createDateTime("lastStatusUpdate", java.util.Date.class);

    public final DateTimePath<java.util.Date> resolutionDate = createDateTime("resolutionDate", java.util.Date.class);

    public final DateTimePath<java.util.Date> responseDate = createDateTime("responseDate", java.util.Date.class);

    public final QStatus status;

    public final NumberPath<Integer> supervisorId = createNumber("supervisorId", Integer.class);

    public final QTicket ticket;

    public final SetPath<TicketActivity, QTicketActivity> ticketActivities = this.<TicketActivity, QTicketActivity>createSet("ticketActivities", TicketActivity.class, QTicketActivity.class, PathInits.DIRECT2);

    public final QTicketGroup ticketGroup;

    public final QUnit unit;

    public final StringPath updateBy = createString("updateBy");

    public final DateTimePath<java.util.Date> updateDate = createDateTime("updateDate", java.util.Date.class);

    public QTicketAssignment(String variable) {
        this(TicketAssignment.class, forVariable(variable), INITS);
    }

    @SuppressWarnings("all")
    public QTicketAssignment(Path<? extends TicketAssignment> path) {
        this((Class)path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QTicketAssignment(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QTicketAssignment(PathMetadata<?> metadata, PathInits inits) {
        this(TicketAssignment.class, metadata, inits);
    }

    public QTicketAssignment(Class<? extends TicketAssignment> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.group = inits.isInitialized("group") ? new QGroup(forProperty("group")) : null;
        this.status = inits.isInitialized("status") ? new QStatus(forProperty("status")) : null;
        this.ticket = inits.isInitialized("ticket") ? new QTicket(forProperty("ticket"), inits.get("ticket")) : null;
        this.ticketGroup = inits.isInitialized("ticketGroup") ? new QTicketGroup(forProperty("ticketGroup"), inits.get("ticketGroup")) : null;
        this.unit = inits.isInitialized("unit") ? new QUnit(forProperty("unit"), inits.get("unit")) : null;
    }

}

