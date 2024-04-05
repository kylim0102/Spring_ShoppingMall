package lhc.portfolio_test.entity;

import jakarta.persistence.*;
import lhc.portfolio_test.dto.CartDTO;
import lombok.*;

@Entity
@Getter
@Table(name = "cart")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CartEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_products_idx")
    private ProductEntity cartProducts;

    @Setter
    private String username;

    @Setter
    private int quantity;

    public CartEntity(ProductEntity cartProducts, String username, int quantity) {
        this.cartProducts = cartProducts;
        this.username = username;
        this.quantity = quantity;
    }

    public CartDTO toDTO() {
        return CartDTO.builder()
                .idx(idx)
                .cartProducts(cartProducts)
                .username(username)
                .quantity(quantity)
                .build();
    }
}
