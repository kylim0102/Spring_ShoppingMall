package lhc.portfolio_test.service;

import lhc.portfolio_test.dto.CartDTO;
import lhc.portfolio_test.entity.CartEntity;
import lhc.portfolio_test.repository.CartRepository;
import lhc.portfolio_test.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CartService {

    private final CartRepository cartRepository;
    private final ProductRepository productRepository;

    public CartService(CartRepository cartRepository, ProductRepository productRepository) {
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
    }

    @Transactional
    public void saveCart(String username, int quantity, Long idx) {
        productRepository.findById(idx);

        CartEntity existingCart = cartRepository.findByUserAndProductId(username, idx);
        if (existingCart != null) {
            existingCart.updateQuantity(quantity);
        } else {
            CartEntity cartEntity = new CartEntity(username, product, quantity);
            cartRepository.save(cartEntity);
        }
    }

    @Transactional
    public List<CartDTO> getCartProducts(String username) {
        List<CartEntity> carts = cartRepository.findByUserId(username);
        return carts.stream()
                .map(cart -> new CartDTO(cart))
                .collect(Collectors.toList());
    }
}
