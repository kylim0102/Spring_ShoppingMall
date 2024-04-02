package lhc.portfolio_test;

import lhc.portfolio_test.entity.ProductEntity;
import lhc.portfolio_test.repository.ProductCategoryRepository;
import lhc.portfolio_test.repository.ProductRepositoryCustom;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
@Transactional
@Commit
class PortfolioApplicationTests {
	@Autowired
	ProductCategoryRepository categoryRepository;
	@Autowired
	ProductRepositoryCustom productRepositoryCustom;

/*
	@Test
	@DisplayName("서브카테고리추가")
	void subCategoryAdd() {
		CategoryEntity subCategory = new CategoryEntity("반바지");

		CategoryEntity parentCategory = categoryRepository.findById(3L).get();

		parentCategory.addSubCategory(subCategory);

	}
*/

	@Test
	void subFind() {
		String CategoryName = "OUTER";
		Pageable pageable = PageRequest.of(0, 10);
		Page<ProductEntity> productsBySubCategoryName = productRepositoryCustom.findProductsByParentCategoryName(CategoryName, pageable);

		List<ProductEntity> productList = productsBySubCategoryName.getContent();
		for (ProductEntity product : productList) {
			System.out.println("Product = " + product);
		}
	}
}
