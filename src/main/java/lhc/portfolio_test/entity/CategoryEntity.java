package lhc.portfolio_test.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "Category_table")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CategoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long idx;

    @Column(name = "category_name", unique = true, nullable = false)
    private String categoryName;

    @ColumnDefault("0")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_category_idx")
    private CategoryEntity parentCategory;

    @OneToMany(mappedBy = "parentCategory", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CategoryEntity> subCategories = new ArrayList<>();

    @OneToMany(mappedBy = "category")
    private List<ProductEntity> products = new ArrayList<>();

    public CategoryEntity(Long categoryIdx) {
        this.idx = categoryIdx;
    }

    public void addSubCategory(CategoryEntity subcategory) {
        this.subCategories.add(subcategory);
        subcategory.setParentCategory(this);
    }

    public CategoryEntity(String categoryName) {
        this.categoryName = categoryName;
    }


}