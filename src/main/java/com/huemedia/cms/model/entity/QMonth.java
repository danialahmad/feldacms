package com.huemedia.cms.model.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QMonth is a Querydsl query type for Month
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QMonth extends EntityPathBase<Month> {

    private static final long serialVersionUID = 123951572;

    public static final QMonth month = new QMonth("month");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath nameEn = createString("nameEn");

    public final StringPath nameMs = createString("nameMs");

    public QMonth(String variable) {
        super(Month.class, forVariable(variable));
    }

    @SuppressWarnings("all")
    public QMonth(Path<? extends Month> path) {
        super((Class)path.getType(), path.getMetadata());
    }

    public QMonth(PathMetadata<?> metadata) {
        super(Month.class, metadata);
    }

}

