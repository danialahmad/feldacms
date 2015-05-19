package com.huemedia.cms.model.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QSlaEscalation is a Querydsl query type for SlaEscalation
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QSlaEscalation extends EntityPathBase<SlaEscalation> {

    private static final long serialVersionUID = -224078543;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QSlaEscalation slaEscalation = new QSlaEscalation("slaEscalation");

    public final StringPath createBy = createString("createBy");

    public final DateTimePath<java.util.Date> createDate = createDateTime("createDate", java.util.Date.class);

    public final QEscalation escalation;

    public final QGroup group;

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final QPriority priority;

    public final StringPath sendTo = createString("sendTo");

    public final BooleanPath sendToAssignee = createBoolean("sendToAssignee");

    public final BooleanPath sendToOwner = createBoolean("sendToOwner");

    public final BooleanPath sendToSupervisor = createBoolean("sendToSupervisor");

    public final QSla sla;

    public final QStatus status;

    public final StringPath updateBy = createString("updateBy");

    public final DateTimePath<java.util.Date> updateDate = createDateTime("updateDate", java.util.Date.class);

    public QSlaEscalation(String variable) {
        this(SlaEscalation.class, forVariable(variable), INITS);
    }

    @SuppressWarnings("all")
    public QSlaEscalation(Path<? extends SlaEscalation> path) {
        this((Class)path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QSlaEscalation(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QSlaEscalation(PathMetadata<?> metadata, PathInits inits) {
        this(SlaEscalation.class, metadata, inits);
    }

    public QSlaEscalation(Class<? extends SlaEscalation> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.escalation = inits.isInitialized("escalation") ? new QEscalation(forProperty("escalation")) : null;
        this.group = inits.isInitialized("group") ? new QGroup(forProperty("group")) : null;
        this.priority = inits.isInitialized("priority") ? new QPriority(forProperty("priority")) : null;
        this.sla = inits.isInitialized("sla") ? new QSla(forProperty("sla")) : null;
        this.status = inits.isInitialized("status") ? new QStatus(forProperty("status")) : null;
    }

}

