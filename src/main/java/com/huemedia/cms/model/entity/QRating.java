package com.huemedia.cms.model.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QRating is a Querydsl query type for Rating
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QRating extends EntityPathBase<Rating> {

    private static final long serialVersionUID = -322083639;

    public static final QRating rating = new QRating("rating");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath name = createString("name");

    public final NumberPath<Integer> rank = createNumber("rank", Integer.class);

    public final SetPath<Ticket, QTicket> tickets = this.<Ticket, QTicket>createSet("tickets", Ticket.class, QTicket.class, PathInits.DIRECT2);

    public QRating(String variable) {
        super(Rating.class, forVariable(variable));
    }

    @SuppressWarnings("all")
    public QRating(Path<? extends Rating> path) {
        super((Class)path.getType(), path.getMetadata());
    }

    public QRating(PathMetadata<?> metadata) {
        super(Rating.class, metadata);
    }

}

