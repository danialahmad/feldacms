package com.huemedia.cms.model.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QKnowledgeFile is a Querydsl query type for KnowledgeFile
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QKnowledgeFile extends EntityPathBase<KnowledgeFile> {

    private static final long serialVersionUID = -1057290642;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QKnowledgeFile knowledgeFile = new QKnowledgeFile("knowledgeFile");

    public final StringPath createBy = createString("createBy");

    public final DateTimePath<java.util.Date> createDate = createDateTime("createDate", java.util.Date.class);

    public final ArrayPath<Byte> data = createArray("data", Byte[].class);

    public final StringPath description = createString("description");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final QKnowledge knowledge;

    public final StringPath mime = createString("mime");

    public final StringPath name = createString("name");

    public final NumberPath<Long> size = createNumber("size", Long.class);

    public final StringPath updateBy = createString("updateBy");

    public final DateTimePath<java.util.Date> updateDate = createDateTime("updateDate", java.util.Date.class);

    public QKnowledgeFile(String variable) {
        this(KnowledgeFile.class, forVariable(variable), INITS);
    }

    @SuppressWarnings("all")
    public QKnowledgeFile(Path<? extends KnowledgeFile> path) {
        this((Class)path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QKnowledgeFile(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QKnowledgeFile(PathMetadata<?> metadata, PathInits inits) {
        this(KnowledgeFile.class, metadata, inits);
    }

    public QKnowledgeFile(Class<? extends KnowledgeFile> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.knowledge = inits.isInitialized("knowledge") ? new QKnowledge(forProperty("knowledge"), inits.get("knowledge")) : null;
    }

}

