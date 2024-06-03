package lhc.portfolio_test.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QProductImgEntity is a Querydsl query type for ProductImgEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QProductImgEntity extends EntityPathBase<ProductImgEntity> {

    private static final long serialVersionUID = -422552636L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QProductImgEntity productImgEntity = new QProductImgEntity("productImgEntity");

    public final NumberPath<Long> idx = createNumber("idx", Long.class);

    public final StringPath o_file = createString("o_file");

    public final QProductEntity productEntity;

    public final StringPath s_file = createString("s_file");

    public QProductImgEntity(String variable) {
        this(ProductImgEntity.class, forVariable(variable), INITS);
    }

    public QProductImgEntity(Path<? extends ProductImgEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QProductImgEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QProductImgEntity(PathMetadata metadata, PathInits inits) {
        this(ProductImgEntity.class, metadata, inits);
    }

    public QProductImgEntity(Class<? extends ProductImgEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.productEntity = inits.isInitialized("productEntity") ? new QProductEntity(forProperty("productEntity"), inits.get("productEntity")) : null;
    }

}

