package com.huemedia.cms.model.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QChat is a Querydsl query type for Chat
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QChat extends EntityPathBase<Chat> {

    private static final long serialVersionUID = 1666261380;

    public static final QChat chat = new QChat("chat");

    public final StringPath createBy = createString("createBy");

    public final DateTimePath<java.util.Date> createDate = createDateTime("createDate", java.util.Date.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath message = createString("message");

    public final StringPath ticketId = createString("ticketId");

    public QChat(String variable) {
        super(Chat.class, forVariable(variable));
    }

    @SuppressWarnings("all")
    public QChat(Path<? extends Chat> path) {
        super((Class)path.getType(), path.getMetadata());
    }

    public QChat(PathMetadata<?> metadata) {
        super(Chat.class, metadata);
    }

}

