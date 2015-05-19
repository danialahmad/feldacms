package com.huemedia.cms.model.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QActivityFile is a Querydsl query type for ActivityFile
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QActivityFile extends EntityPathBase<ActivityFile> {

    private static final long serialVersionUID = -209264809;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QActivityFile activityFile = new QActivityFile("activityFile");

    public final StringPath createBy = createString("createBy");

    public final DateTimePath<java.util.Date> createDate = createDateTime("createDate", java.util.Date.class);

    public final ArrayPath<Byte> data = createArray("data", Byte[].class);

    public final StringPath description = createString("description");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath mime = createString("mime");

    public final StringPath name = createString("name");

    public final NumberPath<Long> size = createNumber("size", Long.class);

    public final QTicketActivity ticketActivity;

    public final StringPath updateBy = createString("updateBy");

    public final DateTimePath<java.util.Date> updateDate = createDateTime("updateDate", java.util.Date.class);

    public QActivityFile(String variable) {
        this(ActivityFile.class, forVariable(variable), INITS);
    }

    @SuppressWarnings("all")
    public QActivityFile(Path<? extends ActivityFile> path) {
        this((Class)path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QActivityFile(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QActivityFile(PathMetadata<?> metadata, PathInits inits) {
        this(ActivityFile.class, metadata, inits);
    }

    public QActivityFile(Class<? extends ActivityFile> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.ticketActivity = inits.isInitialized("ticketActivity") ? new QTicketActivity(forProperty("ticketActivity"), inits.get("ticketActivity")) : null;
    }

}

