package com.huemedia.cms.model.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QTicketGroup is a Querydsl query type for TicketGroup
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QTicketGroup extends EntityPathBase<TicketGroup> {

    private static final long serialVersionUID = 141560615;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QTicketGroup ticketGroup = new QTicketGroup("ticketGroup");

    public final StringPath createBy = createString("createBy");

    public final DateTimePath<java.util.Date> createDate = createDateTime("createDate", java.util.Date.class);

    public final QGroup group;

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath name = createString("name");

    public final SetPath<TicketAssignment, QTicketAssignment> ticketAssignments = this.<TicketAssignment, QTicketAssignment>createSet("ticketAssignments", TicketAssignment.class, QTicketAssignment.class, PathInits.DIRECT2);

    public final SetPath<TicketCategory, QTicketCategory> ticketCategories = this.<TicketCategory, QTicketCategory>createSet("ticketCategories", TicketCategory.class, QTicketCategory.class, PathInits.DIRECT2);

    public final SetPath<Ticket, QTicket> tickets = this.<Ticket, QTicket>createSet("tickets", Ticket.class, QTicket.class, PathInits.DIRECT2);

    public final StringPath updateBy = createString("updateBy");

    public final DateTimePath<java.util.Date> updateDate = createDateTime("updateDate", java.util.Date.class);

    public QTicketGroup(String variable) {
        this(TicketGroup.class, forVariable(variable), INITS);
    }

    @SuppressWarnings("all")
    public QTicketGroup(Path<? extends TicketGroup> path) {
        this((Class)path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QTicketGroup(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QTicketGroup(PathMetadata<?> metadata, PathInits inits) {
        this(TicketGroup.class, metadata, inits);
    }

    public QTicketGroup(Class<? extends TicketGroup> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.group = inits.isInitialized("group") ? new QGroup(forProperty("group")) : null;
    }

}

