package com.huemedia.cms.model.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QTicketFile is a Querydsl query type for TicketFile
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QTicketFile extends EntityPathBase<TicketFile> {

    private static final long serialVersionUID = 1251453908;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QTicketFile ticketFile = new QTicketFile("ticketFile");

    public final StringPath createBy = createString("createBy");

    public final DateTimePath<java.util.Date> createDate = createDateTime("createDate", java.util.Date.class);

    public final ArrayPath<Byte> data = createArray("data", Byte[].class);

    public final StringPath description = createString("description");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath mime = createString("mime");

    public final StringPath name = createString("name");

    public final NumberPath<Long> size = createNumber("size", Long.class);

    public final QTicket ticket;

    public final StringPath updateBy = createString("updateBy");

    public final DateTimePath<java.util.Date> updateDate = createDateTime("updateDate", java.util.Date.class);

    public QTicketFile(String variable) {
        this(TicketFile.class, forVariable(variable), INITS);
    }

    @SuppressWarnings("all")
    public QTicketFile(Path<? extends TicketFile> path) {
        this((Class)path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QTicketFile(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QTicketFile(PathMetadata<?> metadata, PathInits inits) {
        this(TicketFile.class, metadata, inits);
    }

    public QTicketFile(Class<? extends TicketFile> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.ticket = inits.isInitialized("ticket") ? new QTicket(forProperty("ticket"), inits.get("ticket")) : null;
    }

}

