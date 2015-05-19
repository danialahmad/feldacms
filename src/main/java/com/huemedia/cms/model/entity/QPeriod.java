package com.huemedia.cms.model.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QPeriod is a Querydsl query type for Period
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QPeriod extends EntityPathBase<Period> {

    private static final long serialVersionUID = -375707411;

    public static final QPeriod period = new QPeriod("period");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath rangeFrom = createString("rangeFrom");

    public final StringPath rangeName = createString("rangeName");

    public final StringPath rangeTo = createString("rangeTo");

    public QPeriod(String variable) {
        super(Period.class, forVariable(variable));
    }

    @SuppressWarnings("all")
    public QPeriod(Path<? extends Period> path) {
        super((Class)path.getType(), path.getMetadata());
    }

    public QPeriod(PathMetadata<?> metadata) {
        super(Period.class, metadata);
    }

}

