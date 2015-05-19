package com.huemedia.cms.model.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QSla is a Querydsl query type for Sla
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QSla extends EntityPathBase<Sla> {

    private static final long serialVersionUID = 746502524;

    public static final QSla sla = new QSla("sla");

    public final StringPath createBy = createString("createBy");

    public final DateTimePath<java.util.Date> createDate = createDateTime("createDate", java.util.Date.class);

    public final StringPath description = createString("description");

    public final StringPath groupId = createString("groupId");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath priority = createString("priority");

    public final NumberPath<Integer> reminder = createNumber("reminder", Integer.class);

    public final StringPath reminderUnit = createString("reminderUnit");

    public final SetPath<SlaEscalation, QSlaEscalation> slaEscalations = this.<SlaEscalation, QSlaEscalation>createSet("slaEscalations", SlaEscalation.class, QSlaEscalation.class, PathInits.DIRECT2);

    public final SetPath<SlaTask, QSlaTask> slaTasks = this.<SlaTask, QSlaTask>createSet("slaTasks", SlaTask.class, QSlaTask.class, PathInits.DIRECT2);

    public final StringPath status = createString("status");

    public final StringPath ticketCategory = createString("ticketCategory");

    public final StringPath ticketGroup = createString("ticketGroup");

    public final NumberPath<Integer> time = createNumber("time", Integer.class);

    public final StringPath timeUnit = createString("timeUnit");

    public final StringPath title = createString("title");

    public final StringPath updateBy = createString("updateBy");

    public final DateTimePath<java.util.Date> updateDate = createDateTime("updateDate", java.util.Date.class);

    public QSla(String variable) {
        super(Sla.class, forVariable(variable));
    }

    @SuppressWarnings("all")
    public QSla(Path<? extends Sla> path) {
        super((Class)path.getType(), path.getMetadata());
    }

    public QSla(PathMetadata<?> metadata) {
        super(Sla.class, metadata);
    }

}

