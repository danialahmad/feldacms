package com.huemedia.cms.model.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QTicketActivity is a Querydsl query type for TicketActivity
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QTicketActivity extends EntityPathBase<TicketActivity> {

    private static final long serialVersionUID = 1707130215;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QTicketActivity ticketActivity = new QTicketActivity("ticketActivity");

    public final SetPath<ActivityFile, QActivityFile> activityFiles = this.<ActivityFile, QActivityFile>createSet("activityFiles", ActivityFile.class, QActivityFile.class, PathInits.DIRECT2);

    public final QActivityType activityType;

    public final StringPath agenda = createString("agenda");

    public final StringPath createBy = createString("createBy");

    public final DateTimePath<java.util.Date> createDate = createDateTime("createDate", java.util.Date.class);

    public final DateTimePath<java.util.Date> date = createDateTime("date", java.util.Date.class);

    public final StringPath description = createString("description");

    public final DateTimePath<java.util.Date> endTime = createDateTime("endTime", java.util.Date.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath location = createString("location");

    public final StringPath message = createString("message");

    public final BooleanPath sendEmail = createBoolean("sendEmail");

    public final StringPath sendTo = createString("sendTo");

    public final DateTimePath<java.util.Date> startTime = createDateTime("startTime", java.util.Date.class);

    public final StringPath subject = createString("subject");

    public final QTicket ticket;

    public final QTicketAssignment ticketAssignment;

    public final StringPath updateBy = createString("updateBy");

    public final DateTimePath<java.util.Date> updateDate = createDateTime("updateDate", java.util.Date.class);

    public QTicketActivity(String variable) {
        this(TicketActivity.class, forVariable(variable), INITS);
    }

    @SuppressWarnings("all")
    public QTicketActivity(Path<? extends TicketActivity> path) {
        this((Class)path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QTicketActivity(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QTicketActivity(PathMetadata<?> metadata, PathInits inits) {
        this(TicketActivity.class, metadata, inits);
    }

    public QTicketActivity(Class<? extends TicketActivity> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.activityType = inits.isInitialized("activityType") ? new QActivityType(forProperty("activityType")) : null;
        this.ticket = inits.isInitialized("ticket") ? new QTicket(forProperty("ticket"), inits.get("ticket")) : null;
        this.ticketAssignment = inits.isInitialized("ticketAssignment") ? new QTicketAssignment(forProperty("ticketAssignment"), inits.get("ticketAssignment")) : null;
    }

}

