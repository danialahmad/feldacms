package com.huemedia.cms.model.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QCountry is a Querydsl query type for Country
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QCountry extends EntityPathBase<Country> {

    private static final long serialVersionUID = -1420424726;

    public static final QCountry country1 = new QCountry("country1");

    public final StringPath continent = createString("continent");

    public final StringPath country = createString("country");

    public final StringPath currencyCode = createString("currencyCode");

    public final StringPath currencyName = createString("currencyName");

    public final StringPath fips = createString("fips");

    public final StringPath flagActive = createString("flagActive");

    public final StringPath geonameid = createString("geonameid");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath iso = createString("iso");

    public final StringPath iso3 = createString("iso3");

    public final StringPath languages = createString("languages");

    public final StringPath phonePrefix = createString("phonePrefix");

    public final SetPath<Plan, QPlan> plans = this.<Plan, QPlan>createSet("plans", Plan.class, QPlan.class, PathInits.DIRECT2);

    public final StringPath postalCode = createString("postalCode");

    public final SetPath<Profile, QProfile> profiles = this.<Profile, QProfile>createSet("profiles", Profile.class, QProfile.class, PathInits.DIRECT2);

    public final SetPath<State, QState> states = this.<State, QState>createSet("states", State.class, QState.class, PathInits.DIRECT2);

    public QCountry(String variable) {
        super(Country.class, forVariable(variable));
    }

    @SuppressWarnings("all")
    public QCountry(Path<? extends Country> path) {
        super((Class)path.getType(), path.getMetadata());
    }

    public QCountry(PathMetadata<?> metadata) {
        super(Country.class, metadata);
    }

}

