package com.huemedia.cms.model.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QAnnouncement is a Querydsl query type for Announcement
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QAnnouncement extends EntityPathBase<Announcement> {

    private static final long serialVersionUID = -1680871565;

    public static final QAnnouncement announcement = new QAnnouncement("announcement");

    public final BooleanPath approve = createBoolean("approve");

    public final StringPath createdBy = createString("createdBy");

    public final DateTimePath<java.util.Date> createdDate = createDateTime("createdDate", java.util.Date.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath message = createString("message");

    public final BooleanPath publish = createBoolean("publish");

    public final StringPath title = createString("title");

    public final StringPath updatedBy = createString("updatedBy");

    public final DateTimePath<java.util.Date> updatedDate = createDateTime("updatedDate", java.util.Date.class);

    public QAnnouncement(String variable) {
        super(Announcement.class, forVariable(variable));
    }

    @SuppressWarnings("all")
    public QAnnouncement(Path<? extends Announcement> path) {
        super((Class)path.getType(), path.getMetadata());
    }

    public QAnnouncement(PathMetadata<?> metadata) {
        super(Announcement.class, metadata);
    }

}

