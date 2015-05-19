package com.huemedia.cms.model.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QChatImg is a Querydsl query type for ChatImg
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QChatImg extends EntityPathBase<ChatImg> {

    private static final long serialVersionUID = -1639161953;

    public static final QChatImg chatImg = new QChatImg("chatImg");

    public final StringPath createBy = createString("createBy");

    public final DateTimePath<java.util.Date> createDate = createDateTime("createDate", java.util.Date.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath path = createString("path");

    public final StringPath ticketId = createString("ticketId");

    public QChatImg(String variable) {
        super(ChatImg.class, forVariable(variable));
    }

    @SuppressWarnings("all")
    public QChatImg(Path<? extends ChatImg> path) {
        super((Class)path.getType(), path.getMetadata());
    }

    public QChatImg(PathMetadata<?> metadata) {
        super(ChatImg.class, metadata);
    }

}

