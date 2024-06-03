    package lhc.portfolio_test.entity;

    import jakarta.persistence.*;
    import lombok.Builder;
    import lombok.Getter;
    import lombok.NoArgsConstructor;
    import lombok.Setter;
    import org.hibernate.annotations.CreationTimestamp;

    import java.time.LocalDate;
    import java.util.ArrayList;
    import java.util.List;

    @Entity
    @Getter
    @Table(name = "product")
    @NoArgsConstructor
    public class ProductEntity {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long idx;

        @Setter
        @Column (length = 250, nullable = false)
        private String p_name;

        @Setter
        @Column (nullable = false)
        private int p_price;

        @Lob
        @Setter
        @Column (length = 1000, nullable = false)
        private String p_contents;

        @Setter
        @CreationTimestamp
        private LocalDate posted_date;

        @Setter
        @Column (nullable = false)
        private Long inventory;

        @Setter
        @Column
        private int hits;

        @Setter
        @Column
        private int fileAttached;

        @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
        @JoinColumn(name = "category_idx")
        private CategoryEntity category;

        @OneToMany(mappedBy = "productEntity", cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.LAZY)
        private List<ProductImgEntity> productImgEntityList = new ArrayList<>();

        @Builder
        public ProductEntity (String p_name, int p_price, String p_contents, Long inventory, int hits, CategoryEntity category) {
            this.p_name = p_name;
            this.p_price = p_price;
            this.p_contents = p_contents;
            this.inventory = inventory;
            this.category = category;
            this.hits = hits;
        }

        public void setCategory(CategoryEntity category) {
            if (category != null) {
                this.category = category;
            }
        }


        @Override
        public String toString() {
            return "ProductEntity{" +
                    "idx=" + idx +
                    ", p_name='" + p_name + '\'' +
                    ", p_price=" + p_price +
                    '}';
        }
    }
