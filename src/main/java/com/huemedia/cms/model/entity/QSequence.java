package com.huemedia.cms.model.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QSequence is a Querydsl query type for Sequence
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QSequence extends EntityPathBase<Sequence> {

    private static final long serialVersionUID = 638062573;

    public static final QSequence sequence = new QSequence("sequence");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final NumberPath<Integer> month = createNumber("month", Integer.class);

    public final NumberPath<Integer> no = createNumber("no", Integer.class);

    public final StringPath prefix = createString("prefix");

    public final NumberPath<Integer> year = createNumber("year", Integer.class);

    public QSequence(String variable) {
        super(Sequence.class, forVariable(variable));
    }

    @SuppressWarnings("all")
    public QSequence(Path<? extends Sequence> path) {
        super((Class)path.getType(), path.getMetadata());
    }

    public QSequence(PathMetadata<?> metadata) {
        super(Sequence.class, metadata);
    }

}

