package com.huemedia.cms.model.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QActivityType is a Querydsl query type for ActivityType
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QActivityType extends EntityPathBase<ActivityType> {

    private static final long serialVersionUID = -208832235;

    public static final QActivityType activityType = new QActivityType("activityType");

    public final StringPath createBy = createString("createBy");

    public final DateTimePath<java.util.Date> createDate = createDateTime("createDate", java.util.Date.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath name = createString("name");

    public final SetPath<TicketActivity, QTicketActivity> ticketActivities = this.<TicketActivity, QTicketActivity>createSet("ticketActivities", TicketActivity.class, QTicketActivity.class, PathInits.DIRECT2);

    public final StringPath updateBy = createString("updateBy");

    public final DateTimePath<java.util.Date> updateDate = createDateTime("updateDate", java.util.Date.class);

    public QActivityType(String variable) {
        super(ActivityType.class, forVariable(variable));
    }

    @SuppressWarnings("all")
    public QActivityType(Path<? extends ActivityType> path) {
        super((Class)path.getType(), path.getMetadata());
    }

    public QActivityType(PathMetadata<?> metadata) {
        super(ActivityType.class, metadata);
    }

}

