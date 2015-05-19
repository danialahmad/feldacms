package com.huemedia.cms.model.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QOriginator is a Querydsl query type for Originator
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QOriginator extends EntityPathBase<Originator> {

    private static final long serialVersionUID = 1573503656;

    public static final QOriginator originator = new QOriginator("originator");

    public final StringPath createBy = createString("createBy");

    public final DateTimePath<java.util.Date> createDate = createDateTime("createDate", java.util.Date.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath name = createString("name");

    public final SetPath<Ticket, QTicket> tickets = this.<Ticket, QTicket>createSet("tickets", Ticket.class, QTicket.class, PathInits.DIRECT2);

    public final StringPath updateBy = createString("updateBy");

    public final DateTimePath<java.util.Date> updateDate = createDateTime("updateDate", java.util.Date.class);

    public QOriginator(String variable) {
        super(Originator.class, forVariable(variable));
    }

    @SuppressWarnings("all")
    public QOriginator(Path<? extends Originator> path) {
        super((Class)path.getType(), path.getMetadata());
    }

    public QOriginator(PathMetadata<?> metadata) {
        super(Originator.class, metadata);
    }

}

