package com.huemedia.cms.model.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QStatus is a Querydsl query type for Status
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QStatus extends EntityPathBase<Status> {

    private static final long serialVersionUID = -276462818;

    public static final QStatus status = new QStatus("status");

    public final StringPath createBy = createString("createBy");

    public final DateTimePath<java.util.Date> createDate = createDateTime("createDate", java.util.Date.class);

    public final StringPath id = createString("id");

    public final StringPath name = createString("name");

    public final NumberPath<Integer> rank = createNumber("rank", Integer.class);

    public final SetPath<SlaEscalation, QSlaEscalation> slaEscalations = this.<SlaEscalation, QSlaEscalation>createSet("slaEscalations", SlaEscalation.class, QSlaEscalation.class, PathInits.DIRECT2);

    public final SetPath<TicketAssignment, QTicketAssignment> ticketAssignments = this.<TicketAssignment, QTicketAssignment>createSet("ticketAssignments", TicketAssignment.class, QTicketAssignment.class, PathInits.DIRECT2);

    public final SetPath<Ticket, QTicket> tickets = this.<Ticket, QTicket>createSet("tickets", Ticket.class, QTicket.class, PathInits.DIRECT2);

    public final NumberPath<Integer> type = createNumber("type", Integer.class);

    public final StringPath updateBy = createString("updateBy");

    public final DateTimePath<java.util.Date> updateDate = createDateTime("updateDate", java.util.Date.class);

    public QStatus(String variable) {
        super(Status.class, forVariable(variable));
    }

    @SuppressWarnings("all")
    public QStatus(Path<? extends Status> path) {
        super((Class)path.getType(), path.getMetadata());
    }

    public QStatus(PathMetadata<?> metadata) {
        super(Status.class, metadata);
    }

}

