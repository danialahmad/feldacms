package com.huemedia.cms.model.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QTicketCategory is a Querydsl query type for TicketCategory
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QTicketCategory extends EntityPathBase<TicketCategory> {

    private static final long serialVersionUID = -881359018;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QTicketCategory ticketCategory = new QTicketCategory("ticketCategory");

    public final StringPath createBy = createString("createBy");

    public final DateTimePath<java.util.Date> createDate = createDateTime("createDate", java.util.Date.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath name = createString("name");

    public final QTicketGroup ticketGroup;

    public final SetPath<Ticket, QTicket> tickets = this.<Ticket, QTicket>createSet("tickets", Ticket.class, QTicket.class, PathInits.DIRECT2);

    public final StringPath updateBy = createString("updateBy");

    public final DateTimePath<java.util.Date> updateDate = createDateTime("updateDate", java.util.Date.class);

    public QTicketCategory(String variable) {
        this(TicketCategory.class, forVariable(variable), INITS);
    }

    @SuppressWarnings("all")
    public QTicketCategory(Path<? extends TicketCategory> path) {
        this((Class)path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QTicketCategory(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QTicketCategory(PathMetadata<?> metadata, PathInits inits) {
        this(TicketCategory.class, metadata, inits);
    }

    public QTicketCategory(Class<? extends TicketCategory> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.ticketGroup = inits.isInitialized("ticketGroup") ? new QTicketGroup(forProperty("ticketGroup"), inits.get("ticketGroup")) : null;
    }

}

