package com.huemedia.cms.model.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QKnowledge is a Querydsl query type for Knowledge
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QKnowledge extends EntityPathBase<Knowledge> {

    private static final long serialVersionUID = 968676818;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QKnowledge knowledge = new QKnowledge("knowledge");

    public final BooleanPath approved = createBoolean("approved");

    public final StringPath createBy = createString("createBy");

    public final DateTimePath<java.util.Date> createDate = createDateTime("createDate", java.util.Date.class);

    public final StringPath description = createString("description");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final QKnowledgeCategory knowledgeCategory;

    public final SetPath<KnowledgeFile, QKnowledgeFile> knowledgeFiles = this.<KnowledgeFile, QKnowledgeFile>createSet("knowledgeFiles", KnowledgeFile.class, QKnowledgeFile.class, PathInits.DIRECT2);

    public final StringPath solution = createString("solution");

    public final BooleanPath staffOnly = createBoolean("staffOnly");

    public final StringPath status = createString("status");

    public final StringPath title = createString("title");

    public final StringPath updateBy = createString("updateBy");

    public final DateTimePath<java.util.Date> updateDate = createDateTime("updateDate", java.util.Date.class);

    public QKnowledge(String variable) {
        this(Knowledge.class, forVariable(variable), INITS);
    }

    @SuppressWarnings("all")
    public QKnowledge(Path<? extends Knowledge> path) {
        this((Class)path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QKnowledge(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QKnowledge(PathMetadata<?> metadata, PathInits inits) {
        this(Knowledge.class, metadata, inits);
    }

    public QKnowledge(Class<? extends Knowledge> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.knowledgeCategory = inits.isInitialized("knowledgeCategory") ? new QKnowledgeCategory(forProperty("knowledgeCategory")) : null;
    }

}

