package com.huemedia.cms.model.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QNotification is a Querydsl query type for Notification
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QNotification extends EntityPathBase<Notification> {

    private static final long serialVersionUID = -1242420457;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QNotification notification = new QNotification("notification");

    public final StringPath allSupervisorSubject = createString("allSupervisorSubject");

    public final StringPath assigneeSubject = createString("assigneeSubject");

    public final StringPath complainantSubject = createString("complainantSubject");

    public final StringPath createBy = createString("createBy");

    public final DateTimePath<java.util.Date> createDate = createDateTime("createDate", java.util.Date.class);

    public final StringPath helpdeskSubject = createString("helpdeskSubject");

    public final StringPath id = createString("id");

    public final StringPath managerSubject = createString("managerSubject");

    public final NumberPath<Integer> rank = createNumber("rank", Integer.class);

    public final QRefVelocityMailTemplate refVelocityMailTemplateByAllSupervisorTemplate;

    public final QRefVelocityMailTemplate refVelocityMailTemplateByAssigneeTemplate;

    public final QRefVelocityMailTemplate refVelocityMailTemplateByComplainantTemplate;

    public final QRefVelocityMailTemplate refVelocityMailTemplateByHelpdeskTemplate;

    public final QRefVelocityMailTemplate refVelocityMailTemplateByManagerTemplate;

    public final QRefVelocityMailTemplate refVelocityMailTemplateBySupervisorTemplate;

    public final BooleanPath sendAllSupervisor = createBoolean("sendAllSupervisor");

    public final BooleanPath sendToAssignee = createBoolean("sendToAssignee");

    public final BooleanPath sendToComplainant = createBoolean("sendToComplainant");

    public final BooleanPath sendToHelpdesk = createBoolean("sendToHelpdesk");

    public final BooleanPath sendToManager = createBoolean("sendToManager");

    public final BooleanPath sendToSupervisor = createBoolean("sendToSupervisor");

    public final StringPath supervisorSubject = createString("supervisorSubject");

    public final StringPath updateBy = createString("updateBy");

    public final DateTimePath<java.util.Date> updateDate = createDateTime("updateDate", java.util.Date.class);

    public QNotification(String variable) {
        this(Notification.class, forVariable(variable), INITS);
    }

    @SuppressWarnings("all")
    public QNotification(Path<? extends Notification> path) {
        this((Class)path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QNotification(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QNotification(PathMetadata<?> metadata, PathInits inits) {
        this(Notification.class, metadata, inits);
    }

    public QNotification(Class<? extends Notification> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.refVelocityMailTemplateByAllSupervisorTemplate = inits.isInitialized("refVelocityMailTemplateByAllSupervisorTemplate") ? new QRefVelocityMailTemplate(forProperty("refVelocityMailTemplateByAllSupervisorTemplate")) : null;
        this.refVelocityMailTemplateByAssigneeTemplate = inits.isInitialized("refVelocityMailTemplateByAssigneeTemplate") ? new QRefVelocityMailTemplate(forProperty("refVelocityMailTemplateByAssigneeTemplate")) : null;
        this.refVelocityMailTemplateByComplainantTemplate = inits.isInitialized("refVelocityMailTemplateByComplainantTemplate") ? new QRefVelocityMailTemplate(forProperty("refVelocityMailTemplateByComplainantTemplate")) : null;
        this.refVelocityMailTemplateByHelpdeskTemplate = inits.isInitialized("refVelocityMailTemplateByHelpdeskTemplate") ? new QRefVelocityMailTemplate(forProperty("refVelocityMailTemplateByHelpdeskTemplate")) : null;
        this.refVelocityMailTemplateByManagerTemplate = inits.isInitialized("refVelocityMailTemplateByManagerTemplate") ? new QRefVelocityMailTemplate(forProperty("refVelocityMailTemplateByManagerTemplate")) : null;
        this.refVelocityMailTemplateBySupervisorTemplate = inits.isInitialized("refVelocityMailTemplateBySupervisorTemplate") ? new QRefVelocityMailTemplate(forProperty("refVelocityMailTemplateBySupervisorTemplate")) : null;
    }

}

