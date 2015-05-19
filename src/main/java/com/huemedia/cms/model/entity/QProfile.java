package com.huemedia.cms.model.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QProfile is a Querydsl query type for Profile
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QProfile extends EntityPathBase<Profile> {

    private static final long serialVersionUID = 1607285757;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QProfile profile = new QProfile("profile");

    public final StringPath address1 = createString("address1");

    public final StringPath address2 = createString("address2");

    public final StringPath city = createString("city");

    public final StringPath company = createString("company");

    public final QCountry country;

    public final StringPath email = createString("email");

    public final StringPath faxNo = createString("faxNo");

    public final StringPath icNo = createString("icNo");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath mobileNo = createString("mobileNo");

    public final StringPath name = createString("name");

    public final QPersonCategory personCategory;

    public final StringPath phoneNo = createString("phoneNo");

    public final ArrayPath<Byte> photo = createArray("photo", Byte[].class);

    public final QPlan plan;

    public final QRegion region;

    public final QRelation relation;

    public final StringPath settlerNo = createString("settlerNo");

    public final SetPath<StaffGroup, QStaffGroup> staffGroups = this.<StaffGroup, QStaffGroup>createSet("staffGroups", StaffGroup.class, QStaffGroup.class, PathInits.DIRECT2);

    public final StringPath staffNo = createString("staffNo");

    public final QState state;

    public final SetPath<Ticket, QTicket> tickets = this.<Ticket, QTicket>createSet("tickets", Ticket.class, QTicket.class, PathInits.DIRECT2);

    public final SetPath<User, QUser> users = this.<User, QUser>createSet("users", User.class, QUser.class, PathInits.DIRECT2);

    public QProfile(String variable) {
        this(Profile.class, forVariable(variable), INITS);
    }

    @SuppressWarnings("all")
    public QProfile(Path<? extends Profile> path) {
        this((Class)path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QProfile(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QProfile(PathMetadata<?> metadata, PathInits inits) {
        this(Profile.class, metadata, inits);
    }

    public QProfile(Class<? extends Profile> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.country = inits.isInitialized("country") ? new QCountry(forProperty("country")) : null;
        this.personCategory = inits.isInitialized("personCategory") ? new QPersonCategory(forProperty("personCategory")) : null;
        this.plan = inits.isInitialized("plan") ? new QPlan(forProperty("plan"), inits.get("plan")) : null;
        this.region = inits.isInitialized("region") ? new QRegion(forProperty("region")) : null;
        this.relation = inits.isInitialized("relation") ? new QRelation(forProperty("relation")) : null;
        this.state = inits.isInitialized("state") ? new QState(forProperty("state"), inits.get("state")) : null;
    }

}

