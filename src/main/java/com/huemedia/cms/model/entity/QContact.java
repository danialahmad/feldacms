package com.huemedia.cms.model.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QContact is a Querydsl query type for Contact
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QContact extends EntityPathBase<Contact> {

    private static final long serialVersionUID = -1426729356;

    public static final QContact contact = new QContact("contact");

    public final StringPath address1 = createString("address1");

    public final StringPath address2 = createString("address2");

    public final StringPath city = createString("city");

    public final StringPath country = createString("country");

    public final StringPath email = createString("email");

    public final StringPath faxNo = createString("faxNo");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath mobileNo = createString("mobileNo");

    public final StringPath name = createString("name");

    public final StringPath phoneNo = createString("phoneNo");

    public final StringPath state = createString("state");

    public QContact(String variable) {
        super(Contact.class, forVariable(variable));
    }

    @SuppressWarnings("all")
    public QContact(Path<? extends Contact> path) {
        super((Class)path.getType(), path.getMetadata());
    }

    public QContact(PathMetadata<?> metadata) {
        super(Contact.class, metadata);
    }

}

