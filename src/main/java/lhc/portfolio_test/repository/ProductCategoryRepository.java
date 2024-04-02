package lhc.portfolio_test.repository;

import lhc.portfolio_test.dto.SubCategoryDTO;
import lhc.portfolio_test.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductCategoryRepository extends JpaRepository<CategoryEntity, Long> {

    @Query("SELECT new lhc.portfolio_test.dto.SubCategoryDTO(c.idx, c.categoryName) FROM CategoryEntity c WHERE c.parentCategory.idx = :parentIdx")
    List<SubCategoryDTO> findByParentCategoryIdx(@Param("parentIdx") Long parentCategoryIdx);

    List<CategoryEntity> findByIdx (Long idx);

    List<CategoryEntity> findByParentCategory(CategoryEntity parentCategory);

    @Query("SELECT c.idx FROM CategoryEntity c WHERE lower(c.categoryName) = lower(:categoryName)")
    Long findCategoryIdByCategoryName(@Param("categoryName") String categoryName);
}
