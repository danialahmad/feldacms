package com.huemedia.cms.model.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QTicket is a Querydsl query type for Ticket
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QTicket extends EntityPathBase<Ticket> {

    private static final long serialVersionUID = -257941960;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QTicket ticket = new QTicket("ticket");

    public final StringPath action = createString("action");

    public final DateTimePath<java.util.Date> assignDate = createDateTime("assignDate", java.util.Date.class);

    public final NumberPath<Integer> assigneeId = createNumber("assigneeId", Integer.class);

    public final QCategory category;

    public final StringPath comment = createString("comment");

    public final StringPath createBy = createString("createBy");

    public final DateTimePath<java.util.Date> createDate = createDateTime("createDate", java.util.Date.class);

    public final StringPath description = createString("description");

    public final NumberPath<Integer> duration = createNumber("duration", Integer.class);

    public final StringPath groupId = createString("groupId");

    public final StringPath id = createString("id");

    public final StringPath intervalX = createString("intervalX");

    public final DateTimePath<java.util.Date> lastStatusUpdate = createDateTime("lastStatusUpdate", java.util.Date.class);

    public final QOriginator originator;

    public final QPriority priority;

    public final QProfile profile;

    public final QRating rating;

    public final NumberPath<Integer> regionId = createNumber("regionId", Integer.class);

    public final StringPath remarks = createString("remarks");

    public final DateTimePath<java.util.Date> replyDate = createDateTime("replyDate", java.util.Date.class);

    public final DateTimePath<java.util.Date> resolutionDate = createDateTime("resolutionDate", java.util.Date.class);

    public final SetPath<SlaTask, QSlaTask> slaTasks = this.<SlaTask, QSlaTask>createSet("slaTasks", SlaTask.class, QSlaTask.class, PathInits.DIRECT2);

    public final QStatus status;

    public final NumberPath<Integer> supervisorId = createNumber("supervisorId", Integer.class);

    public final SetPath<TicketActivity, QTicketActivity> ticketActivities = this.<TicketActivity, QTicketActivity>createSet("ticketActivities", TicketActivity.class, QTicketActivity.class, PathInits.DIRECT2);

    public final SetPath<TicketAssignment, QTicketAssignment> ticketAssignments = this.<TicketAssignment, QTicketAssignment>createSet("ticketAssignments", TicketAssignment.class, QTicketAssignment.class, PathInits.DIRECT2);

    public final QTicketCategory ticketCategory;

    public final SetPath<TicketFile, QTicketFile> ticketFiles = this.<TicketFile, QTicketFile>createSet("ticketFiles", TicketFile.class, QTicketFile.class, PathInits.DIRECT2);

    public final QTicketGroup ticketGroup;

    public final StringPath ticketTitle = createString("ticketTitle");

    public final StringPath ticketType = createString("ticketType");

    public final StringPath updateBy = createString("updateBy");

    public final DateTimePath<java.util.Date> updateDate = createDateTime("updateDate", java.util.Date.class);

    public QTicket(String variable) {
        this(Ticket.class, forVariable(variable), INITS);
    }

    @SuppressWarnings("all")
    public QTicket(Path<? extends Ticket> path) {
        this((Class)path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QTicket(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QTicket(PathMetadata<?> metadata, PathInits inits) {
        this(Ticket.class, metadata, inits);
    }

    public QTicket(Class<? extends Ticket> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.category = inits.isInitialized("category") ? new QCategory(forProperty("category")) : null;
        this.originator = inits.isInitialized("originator") ? new QOriginator(forProperty("originator")) : null;
        this.priority = inits.isInitialized("priority") ? new QPriority(forProperty("priority")) : null;
        this.profile = inits.isInitialized("profile") ? new QProfile(forProperty("profile"), inits.get("profile")) : null;
        this.rating = inits.isInitialized("rating") ? new QRating(forProperty("rating")) : null;
        this.status = inits.isInitialized("status") ? new QStatus(forProperty("status")) : null;
        this.ticketCategory = inits.isInitialized("ticketCategory") ? new QTicketCategory(forProperty("ticketCategory"), inits.get("ticketCategory")) : null;
        this.ticketGroup = inits.isInitialized("ticketGroup") ? new QTicketGroup(forProperty("ticketGroup"), inits.get("ticketGroup")) : null;
    }

}

