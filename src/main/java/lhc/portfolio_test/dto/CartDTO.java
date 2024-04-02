package lhc.portfolio_test.dto;

import lhc.portfolio_test.entity.ProductEntity;
import lombok.*;

@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CartDTO {
    private Long idx;
    private ProductEntity cartProducts;
    private String username;
    private int quantity;
}
