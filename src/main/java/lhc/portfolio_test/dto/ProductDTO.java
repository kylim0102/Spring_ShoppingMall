package lhc.portfolio_test.dto;

import lhc.portfolio_test.entity.CategoryEntity;
import lhc.portfolio_test.entity.ProductEntity;
import lhc.portfolio_test.entity.ProductImgEntity;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
    private Long idx;
    private String p_name;
    private int p_price;
    private String p_contents;
    private Long inventory;
    private Long category_idx;

    private Long sub_category_idx;

    private CategoryEntity category;
    private String category_name;
    private String subCategories;

    private List<MultipartFile> productImg;
    private List<String> o_file;
    private List<String> s_file;
    private int fileAttached;
    private int hits;
    private LocalDate posted_date;

    public ProductDTO(Long idx, String p_name, int p_price, Long inventory, String p_contents, CategoryEntity category, String category_name, int hits, LocalDate posted_date) {
        this.idx = idx;
        this.p_name = p_name;
        this.p_price = p_price;
        this.inventory = inventory;
        this.p_contents = p_contents;
        this.category = category;
        this.category_name = category_name;
        this.hits = hits;
        this.posted_date = posted_date;
    }

    public ProductEntity ProductToEntity() {
        CategoryEntity categoryEntity = new CategoryEntity(category_idx);
        return ProductEntity.builder()
                .p_name(p_name)
                .p_price(p_price)
                .p_contents(p_contents)
                .inventory(inventory)
                .category(category)
                .build();
    }

    public static ProductDTO toProductDTO(ProductEntity productEntity) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setP_name(productEntity.getP_name());
        productDTO.setP_price(productEntity.getP_price());
        productDTO.setP_contents(productEntity.getP_contents());
        productDTO.setInventory(productEntity.getInventory());
        productDTO.setCategory(productEntity.getCategory());
        productDTO.setSub_category_idx(productEntity.getCategory().getIdx());
        productDTO.setCategory(productEntity.getCategory());
        productDTO.setCategory_name(productEntity.getCategory().getCategoryName());
        productDTO.setIdx(productEntity.getIdx());
        productDTO.setPosted_date(productEntity.getPosted_date());

        if(productEntity.getFileAttached() == 0) {
            productDTO.setFileAttached(productEntity.getFileAttached());
        } else {
            List<String> o_fileList = new ArrayList<>();
            List<String> s_fileList = new ArrayList<>();
            productDTO.setFileAttached(productEntity.getFileAttached());

            for (ProductImgEntity productImgEntity : productEntity.getProductImgEntityList()) {
                o_fileList.add(productImgEntity.getO_file());
                s_fileList.add(productImgEntity.getS_file());
            }
            productDTO.setO_file(o_fileList);
            productDTO.setS_file(s_fileList);
        }
        return productDTO;
    }
}
