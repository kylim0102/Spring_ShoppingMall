package lhc.portfolio_test.service;

import lhc.portfolio_test.dto.ProductDTO;
import lhc.portfolio_test.dto.SubCategoryDTO;
import lhc.portfolio_test.entity.CategoryEntity;
import lhc.portfolio_test.entity.ProductEntity;
import lhc.portfolio_test.entity.ProductImgEntity;
import lhc.portfolio_test.repository.ProductCategoryRepository;
import lhc.portfolio_test.repository.ProductImgRepository;
import lhc.portfolio_test.repository.ProductRepository;
import lhc.portfolio_test.repository.ProductRepositoryCustom;
import lombok.RequiredArgsConstructor;
import net.coobird.thumbnailator.Thumbnailator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductImgRepository productImgRepository;
    private final ProductCategoryRepository productCategoryRepository;
    private final ProductRepositoryCustom productRepositoryCustom;

    @Transactional
    public List<SubCategoryDTO> findSubCategoriesByParentIdx(Long parentIdx) {

        return productCategoryRepository.findByParentCategoryIdx(parentIdx);

    }

    public CategoryEntity save(CategoryEntity categoryEntity) {
        return productCategoryRepository.save(categoryEntity);
    }

    @Transactional
    public void saveSubCategory(Long parentCategoryId, CategoryEntity subCategories) {
        CategoryEntity parentCategory = productCategoryRepository.findById(parentCategoryId).get();
        parentCategory.addSubCategory(subCategories);
    }

    @Value("${save.path}")
    private String path;

    @Transactional
    public void write(ProductDTO productDTO) throws IOException {

        ProductEntity productEntity = productDTO.ProductToEntity();

        if (productDTO.getProductImg().isEmpty()) {
            productEntity.setFileAttached(0);
            productRepository.save(productEntity);

        } else {
            CategoryEntity findSubCategory = productCategoryRepository.findById(productDTO.getSub_category_idx()).orElseThrow();
            ProductEntity product = ProductEntity.builder()
                    .category(findSubCategory)
                    .p_name(productDTO.getP_name())
                    .p_price(productDTO.getP_price())
                    .p_contents(productDTO.getP_contents())
                    .inventory(productDTO.getInventory())
                    .hits(0)
                    .build();
            product.setFileAttached(1);

            ProductEntity products = productRepository.save(product);

            File o_file = null;
            List<String> thumb_file = new ArrayList<>();

            for (MultipartFile productImg : productDTO.getProductImg()) {
                String o_fileName = productImg.getOriginalFilename();
                String s_fileName = System.currentTimeMillis() + "_" + o_fileName;
                thumb_file.add(s_fileName);

                String savePath = path + s_fileName;
                o_file = new File(savePath);
                productImg.transferTo(o_file);


                ProductImgEntity productImgEntity = ProductImgEntity.builder()
                        .o_file(o_fileName)
                        .s_file(s_fileName)
                        .productEntity(products)
                        .build();
                productImgRepository.save(productImgEntity);
            }

            o_file = new File(path + thumb_file.get(0));
            String thumb = "Thumb_" + thumb_file.get(0);
            String thumbPath = path + thumb;
            File files = new File(thumbPath);
            Thumbnailator.createThumbnail(o_file, files, 150, 150);
        }
    }

    @Transactional
    public Page<ProductEntity> findProductsByParentCategoryName(String categoryName, Pageable pageable) {
        return productRepositoryCustom.findProductsByParentCategoryName(categoryName, pageable);
    }

    public List<ProductImgEntity> findProductImages(Long productId) {
        ProductEntity product = productRepository.findById(productId).orElse(null);
        if (product != null) {
            return product.getProductImgEntityList();
        }
        return null;
    }

    public ProductDTO findById(Long idx) {
        ProductEntity product = productRepository.findById(idx).orElse(null);

        ProductDTO dto = null;
        if (product != null) {
            dto = ProductDTO.toProductDTO(product);
        }
        return dto;
    }

    public List<ProductImgEntity> getAllProductImages() {
        return productImgRepository.findAll();
    }

    @Transactional
    public Page<ProductEntity> findProductsByChildCategoryName(String childCategoryName, Pageable pageable) {
        return productRepositoryCustom.findProductsByChildCategoryName(childCategoryName, pageable);
    }
}
