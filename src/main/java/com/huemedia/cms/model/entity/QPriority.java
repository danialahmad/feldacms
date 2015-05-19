package com.huemedia.cms.model.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QPriority is a Querydsl query type for Priority
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QPriority extends EntityPathBase<Priority> {

    private static final long serialVersionUID = -1876946480;

    public static final QPriority priority = new QPriority("priority");

    public final StringPath createBy = createString("createBy");

    public final DateTimePath<java.util.Date> createDate = createDateTime("createDate", java.util.Date.class);

    public final StringPath id = createString("id");

    public final StringPath name = createString("name");

    public final NumberPath<Integer> rank = createNumber("rank", Integer.class);

    public final SetPath<SlaEscalation, QSlaEscalation> slaEscalations = this.<SlaEscalation, QSlaEscalation>createSet("slaEscalations", SlaEscalation.class, QSlaEscalation.class, PathInits.DIRECT2);

    public final SetPath<Ticket, QTicket> tickets = this.<Ticket, QTicket>createSet("tickets", Ticket.class, QTicket.class, PathInits.DIRECT2);

    public final StringPath updateBy = createString("updateBy");

    public final DateTimePath<java.util.Date> updateDate = createDateTime("updateDate", java.util.Date.class);

    public QPriority(String variable) {
        super(Priority.class, forVariable(variable));
    }

    @SuppressWarnings("all")
    public QPriority(Path<? extends Priority> path) {
        super((Class)path.getType(), path.getMetadata());
    }

    public QPriority(PathMetadata<?> metadata) {
        super(Priority.class, metadata);
    }

}

