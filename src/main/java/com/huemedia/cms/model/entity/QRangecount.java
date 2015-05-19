package com.huemedia.cms.model.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QRangecount is a Querydsl query type for Rangecount
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QRangecount extends EntityPathBase<Rangecount> {

    private static final long serialVersionUID = 1645777694;

    public static final QRangecount rangecount = new QRangecount("rangecount");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath name = createString("name");

    public QRangecount(String variable) {
        super(Rangecount.class, forVariable(variable));
    }

    @SuppressWarnings("all")
    public QRangecount(Path<? extends Rangecount> path) {
        super((Class)path.getType(), path.getMetadata());
    }

    public QRangecount(PathMetadata<?> metadata) {
        super(Rangecount.class, metadata);
    }

}

