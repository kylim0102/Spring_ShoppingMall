package lhc.portfolio_test.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Table(name = "product_img")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProductImgEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @Setter
    @Column(length = 250, nullable = false)
    private String o_file;

    @Setter
    @Column(length = 250, nullable = false)
    private String s_file;

    @ManyToOne(fetch = FetchType.LAZY)  //1:n 파일
    @JoinColumn(name = "productEntity_idx")
    private ProductEntity productEntity;

    @Builder
    public ProductImgEntity(String o_file, String s_file, ProductEntity productEntity) {
        this.o_file = o_file;
        this.s_file = s_file;
        this.productEntity = productEntity;
    }

    public static ProductImgEntity toProductImgEntity(String o_file, String s_file) {
        ProductImgEntity productImgEntity = new ProductImgEntity();
        productImgEntity.setO_file(o_file);
        productImgEntity.setS_file(s_file);
        return productImgEntity;
    }

}
