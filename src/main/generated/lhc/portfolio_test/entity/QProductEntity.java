package lhc.portfolio_test.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QProductEntity is a Querydsl query type for ProductEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QProductEntity extends EntityPathBase<ProductEntity> {

    private static final long serialVersionUID = -762238363L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QProductEntity productEntity = new QProductEntity("productEntity");

    public final QCategoryEntity category;

    public final NumberPath<Integer> fileAttached = createNumber("fileAttached", Integer.class);

    public final NumberPath<Integer> hits = createNumber("hits", Integer.class);

    public final NumberPath<Long> idx = createNumber("idx", Long.class);

    public final NumberPath<Long> inventory = createNumber("inventory", Long.class);

    public final StringPath p_contents = createString("p_contents");

    public final StringPath p_name = createString("p_name");

    public final NumberPath<Integer> p_price = createNumber("p_price", Integer.class);

    public final DatePath<java.time.LocalDate> posted_date = createDate("posted_date", java.time.LocalDate.class);

    public final ListPath<ProductImgEntity, QProductImgEntity> productImgEntityList = this.<ProductImgEntity, QProductImgEntity>createList("productImgEntityList", ProductImgEntity.class, QProductImgEntity.class, PathInits.DIRECT2);

    public QProductEntity(String variable) {
        this(ProductEntity.class, forVariable(variable), INITS);
    }

    public QProductEntity(Path<? extends ProductEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QProductEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QProductEntity(PathMetadata metadata, PathInits inits) {
        this(ProductEntity.class, metadata, inits);
    }

    public QProductEntity(Class<? extends ProductEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.category = inits.isInitialized("category") ? new QCategoryEntity(forProperty("category"), inits.get("category")) : null;
    }

}

