package com.huemedia.cms.model.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QKnowledgeCategory is a Querydsl query type for KnowledgeCategory
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QKnowledgeCategory extends EntityPathBase<KnowledgeCategory> {

    private static final long serialVersionUID = 1427637488;

    public static final QKnowledgeCategory knowledgeCategory = new QKnowledgeCategory("knowledgeCategory");

    public final StringPath createBy = createString("createBy");

    public final DateTimePath<java.util.Date> createDate = createDateTime("createDate", java.util.Date.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final SetPath<Knowledge, QKnowledge> knowledges = this.<Knowledge, QKnowledge>createSet("knowledges", Knowledge.class, QKnowledge.class, PathInits.DIRECT2);

    public final StringPath name = createString("name");

    public final StringPath updateBy = createString("updateBy");

    public final DateTimePath<java.util.Date> updateDate = createDateTime("updateDate", java.util.Date.class);

    public QKnowledgeCategory(String variable) {
        super(KnowledgeCategory.class, forVariable(variable));
    }

    @SuppressWarnings("all")
    public QKnowledgeCategory(Path<? extends KnowledgeCategory> path) {
        super((Class)path.getType(), path.getMetadata());
    }

    public QKnowledgeCategory(PathMetadata<?> metadata) {
        super(KnowledgeCategory.class, metadata);
    }

}

