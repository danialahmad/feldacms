package com.huemedia.cms.model.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QSlaTask is a Querydsl query type for SlaTask
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QSlaTask extends EntityPathBase<SlaTask> {

    private static final long serialVersionUID = -210418399;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QSlaTask slaTask = new QSlaTask("slaTask");

    public final NumberPath<Integer> count = createNumber("count", Integer.class);

    public final StringPath createBy = createString("createBy");

    public final DateTimePath<java.util.Date> createDate = createDateTime("createDate", java.util.Date.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final DateTimePath<java.util.Date> nextReminder = createDateTime("nextReminder", java.util.Date.class);

    public final DateTimePath<java.util.Date> nextTrigger = createDateTime("nextTrigger", java.util.Date.class);

    public final QSla sla;

    public final StringPath status = createString("status");

    public final QTicket ticket;

    public final StringPath updateBy = createString("updateBy");

    public final DateTimePath<java.util.Date> updateDate = createDateTime("updateDate", java.util.Date.class);

    public QSlaTask(String variable) {
        this(SlaTask.class, forVariable(variable), INITS);
    }

    @SuppressWarnings("all")
    public QSlaTask(Path<? extends SlaTask> path) {
        this((Class)path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QSlaTask(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QSlaTask(PathMetadata<?> metadata, PathInits inits) {
        this(SlaTask.class, metadata, inits);
    }

    public QSlaTask(Class<? extends SlaTask> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.sla = inits.isInitialized("sla") ? new QSla(forProperty("sla")) : null;
        this.ticket = inits.isInitialized("ticket") ? new QTicket(forProperty("ticket"), inits.get("ticket")) : null;
    }

}

