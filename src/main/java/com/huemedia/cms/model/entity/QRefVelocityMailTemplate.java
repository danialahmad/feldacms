package com.huemedia.cms.model.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QRefVelocityMailTemplate is a Querydsl query type for RefVelocityMailTemplate
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QRefVelocityMailTemplate extends EntityPathBase<RefVelocityMailTemplate> {

    private static final long serialVersionUID = 568994901;

    public static final QRefVelocityMailTemplate refVelocityMailTemplate = new QRefVelocityMailTemplate("refVelocityMailTemplate");

    public final StringPath content = createString("content");

    public final StringPath createBy = createString("createBy");

    public final DateTimePath<java.util.Date> createDate = createDateTime("createDate", java.util.Date.class);

    public final StringPath id = createString("id");

    public final StringPath name = createString("name");

    public final SetPath<Notification, QNotification> notificationsForAllSupervisorTemplate = this.<Notification, QNotification>createSet("notificationsForAllSupervisorTemplate", Notification.class, QNotification.class, PathInits.DIRECT2);

    public final SetPath<Notification, QNotification> notificationsForAssigneeTemplate = this.<Notification, QNotification>createSet("notificationsForAssigneeTemplate", Notification.class, QNotification.class, PathInits.DIRECT2);

    public final SetPath<Notification, QNotification> notificationsForComplainantTemplate = this.<Notification, QNotification>createSet("notificationsForComplainantTemplate", Notification.class, QNotification.class, PathInits.DIRECT2);

    public final SetPath<Notification, QNotification> notificationsForHelpdeskTemplate = this.<Notification, QNotification>createSet("notificationsForHelpdeskTemplate", Notification.class, QNotification.class, PathInits.DIRECT2);

    public final SetPath<Notification, QNotification> notificationsForManagerTemplate = this.<Notification, QNotification>createSet("notificationsForManagerTemplate", Notification.class, QNotification.class, PathInits.DIRECT2);

    public final SetPath<Notification, QNotification> notificationsForSupervisorTemplate = this.<Notification, QNotification>createSet("notificationsForSupervisorTemplate", Notification.class, QNotification.class, PathInits.DIRECT2);

    public final DateTimePath<java.util.Date> timestamp = createDateTime("timestamp", java.util.Date.class);

    public QRefVelocityMailTemplate(String variable) {
        super(RefVelocityMailTemplate.class, forVariable(variable));
    }

    @SuppressWarnings("all")
    public QRefVelocityMailTemplate(Path<? extends RefVelocityMailTemplate> path) {
        super((Class)path.getType(), path.getMetadata());
    }

    public QRefVelocityMailTemplate(PathMetadata<?> metadata) {
        super(RefVelocityMailTemplate.class, metadata);
    }

}

