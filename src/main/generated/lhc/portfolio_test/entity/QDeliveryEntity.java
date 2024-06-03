package lhc.portfolio_test.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QDeliveryEntity is a Querydsl query type for DeliveryEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QDeliveryEntity extends EntityPathBase<DeliveryEntity> {

    private static final long serialVersionUID = 1668775588L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QDeliveryEntity deliveryEntity = new QDeliveryEntity("deliveryEntity");

    public final QAddressEntity addressEntity;

    public final NumberPath<Long> idx = createNumber("idx", Long.class);

    public final QOrderEntity orderEntity;

    public final EnumPath<lhc.portfolio_test.Enum.DeliveryStatus> status = createEnum("status", lhc.portfolio_test.Enum.DeliveryStatus.class);

    public QDeliveryEntity(String variable) {
        this(DeliveryEntity.class, forVariable(variable), INITS);
    }

    public QDeliveryEntity(Path<? extends DeliveryEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QDeliveryEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QDeliveryEntity(PathMetadata metadata, PathInits inits) {
        this(DeliveryEntity.class, metadata, inits);
    }

    public QDeliveryEntity(Class<? extends DeliveryEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.addressEntity = inits.isInitialized("addressEntity") ? new QAddressEntity(forProperty("addressEntity")) : null;
        this.orderEntity = inits.isInitialized("orderEntity") ? new QOrderEntity(forProperty("orderEntity"), inits.get("orderEntity")) : null;
    }

}

