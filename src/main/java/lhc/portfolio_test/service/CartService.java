package lhc.portfolio_test.service;

import lhc.portfolio_test.dto.CartDTO;
import lhc.portfolio_test.entity.ProductEntity;
import lhc.portfolio_test.repository.CartRepository;
import lhc.portfolio_test.repository.ProductRepository;
import org.springframework.stereotype.Service;

@Service
public class CartService {

    private final CartRepository cartRepository;
    private final ProductRepository productRepository;

    public CartService(CartRepository cartRepository, ProductRepository productRepository) {
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
    }

    public CartDTO savecart(String userid, Long idx, Integer quantity) {
        ProductEntity productEntity = productRepository.findById(idx).get();
        CartDTO cartDTO = new CartDTO();
        cartDTO.setUsername(userid);
        cartDTO.setIdx(idx);
        cartDTO.setQuantity(quantity);

        return cartDTO;
    }
}
