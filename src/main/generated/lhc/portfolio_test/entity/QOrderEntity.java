package lhc.portfolio_test.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QOrderEntity is a Querydsl query type for OrderEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QOrderEntity extends EntityPathBase<OrderEntity> {

    private static final long serialVersionUID = 1406071364L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QOrderEntity orderEntity = new QOrderEntity("orderEntity");

    public final QDeliveryEntity deliveryEntity;

    public final NumberPath<Long> idx = createNumber("idx", Long.class);

    public final QMemberEntity memberEntity;

    public final DateTimePath<java.time.LocalDateTime> orderDate = createDateTime("orderDate", java.time.LocalDateTime.class);

    public final ListPath<OrderProductEntity, QOrderProductEntity> orderProductEntities = this.<OrderProductEntity, QOrderProductEntity>createList("orderProductEntities", OrderProductEntity.class, QOrderProductEntity.class, PathInits.DIRECT2);

    public final EnumPath<lhc.portfolio_test.Enum.OrderStatus> status = createEnum("status", lhc.portfolio_test.Enum.OrderStatus.class);

    public QOrderEntity(String variable) {
        this(OrderEntity.class, forVariable(variable), INITS);
    }

    public QOrderEntity(Path<? extends OrderEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QOrderEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QOrderEntity(PathMetadata metadata, PathInits inits) {
        this(OrderEntity.class, metadata, inits);
    }

    public QOrderEntity(Class<? extends OrderEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.deliveryEntity = inits.isInitialized("deliveryEntity") ? new QDeliveryEntity(forProperty("deliveryEntity"), inits.get("deliveryEntity")) : null;
        this.memberEntity = inits.isInitialized("memberEntity") ? new QMemberEntity(forProperty("memberEntity"), inits.get("memberEntity")) : null;
    }

}

