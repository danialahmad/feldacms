package com.huemedia.cms.model.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QGroup is a Querydsl query type for Group
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QGroup extends EntityPathBase<Group> {

    private static final long serialVersionUID = 118500819;

    public static final QGroup group = new QGroup("group1");

    public final StringPath createBy = createString("createBy");

    public final DateTimePath<java.util.Date> createDate = createDateTime("createDate", java.util.Date.class);

    public final StringPath id = createString("id");

    public final StringPath name = createString("name");

    public final SetPath<SlaEscalation, QSlaEscalation> slaEscalations = this.<SlaEscalation, QSlaEscalation>createSet("slaEscalations", SlaEscalation.class, QSlaEscalation.class, PathInits.DIRECT2);

    public final SetPath<StaffGroup, QStaffGroup> staffGroups = this.<StaffGroup, QStaffGroup>createSet("staffGroups", StaffGroup.class, QStaffGroup.class, PathInits.DIRECT2);

    public final SetPath<TicketAssignment, QTicketAssignment> ticketAssignments = this.<TicketAssignment, QTicketAssignment>createSet("ticketAssignments", TicketAssignment.class, QTicketAssignment.class, PathInits.DIRECT2);

    public final SetPath<TicketGroup, QTicketGroup> ticketGroups = this.<TicketGroup, QTicketGroup>createSet("ticketGroups", TicketGroup.class, QTicketGroup.class, PathInits.DIRECT2);

    public final SetPath<Unit, QUnit> units = this.<Unit, QUnit>createSet("units", Unit.class, QUnit.class, PathInits.DIRECT2);

    public final StringPath updateBy = createString("updateBy");

    public final DateTimePath<java.util.Date> updateDate = createDateTime("updateDate", java.util.Date.class);

    public QGroup(String variable) {
        super(Group.class, forVariable(variable));
    }

    @SuppressWarnings("all")
    public QGroup(Path<? extends Group> path) {
        super((Class)path.getType(), path.getMetadata());
    }

    public QGroup(PathMetadata<?> metadata) {
        super(Group.class, metadata);
    }

}

