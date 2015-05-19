package com.huemedia.cms.model.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QEventLog is a Querydsl query type for EventLog
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QEventLog extends EntityPathBase<EventLog> {

    private static final long serialVersionUID = -433384842;

    public static final QEventLog eventLog = new QEventLog("eventLog");

    public final StringPath action = createString("action");

    public final NumberPath<Integer> assignmentId = createNumber("assignmentId", Integer.class);

    public final StringPath createBy = createString("createBy");

    public final DateTimePath<java.util.Date> createDate = createDateTime("createDate", java.util.Date.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath ticketId = createString("ticketId");

    public QEventLog(String variable) {
        super(EventLog.class, forVariable(variable));
    }

    @SuppressWarnings("all")
    public QEventLog(Path<? extends EventLog> path) {
        super((Class)path.getType(), path.getMetadata());
    }

    public QEventLog(PathMetadata<?> metadata) {
        super(EventLog.class, metadata);
    }

}

