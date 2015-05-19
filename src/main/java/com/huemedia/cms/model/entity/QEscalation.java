package com.huemedia.cms.model.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QEscalation is a Querydsl query type for Escalation
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QEscalation extends EntityPathBase<Escalation> {

    private static final long serialVersionUID = -1091360831;

    public static final QEscalation escalation = new QEscalation("escalation");

    public final StringPath action = createString("action");

    public final StringPath createBy = createString("createBy");

    public final DateTimePath<java.util.Date> createDate = createDateTime("createDate", java.util.Date.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final SetPath<SlaEscalation, QSlaEscalation> slaEscalations = this.<SlaEscalation, QSlaEscalation>createSet("slaEscalations", SlaEscalation.class, QSlaEscalation.class, PathInits.DIRECT2);

    public final StringPath updateBy = createString("updateBy");

    public final DateTimePath<java.util.Date> updateDate = createDateTime("updateDate", java.util.Date.class);

    public QEscalation(String variable) {
        super(Escalation.class, forVariable(variable));
    }

    @SuppressWarnings("all")
    public QEscalation(Path<? extends Escalation> path) {
        super((Class)path.getType(), path.getMetadata());
    }

    public QEscalation(PathMetadata<?> metadata) {
        super(Escalation.class, metadata);
    }

}

