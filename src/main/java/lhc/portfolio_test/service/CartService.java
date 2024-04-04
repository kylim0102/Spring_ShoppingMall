package lhc.portfolio_test.service;

import lhc.portfolio_test.dto.CartDTO;
import lhc.portfolio_test.entity.CartEntity;
import lhc.portfolio_test.entity.ProductEntity;
import lhc.portfolio_test.repository.CartRepository;
import lhc.portfolio_test.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartService {

    private final CartRepository cartRepository;
    private final ProductRepository productRepository;

    public CartService(CartRepository cartRepository, ProductRepository productRepository) {
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
    }

    @Transactional
    public CartEntity saveCart(String userid, Long idx, Integer quantity) {
        ProductEntity productEntity = productRepository.findById(idx).get();

        CartEntity cartEntity = new CartEntity(productEntity, userid, quantity);
        return cartRepository.save(cartEntity);

    }

    @Transactional
    public List<CartDTO> findByUserId(String username) {
        List<CartEntity> cartEntityList = cartRepository.findByUsername(username);
        List<CartDTO> cartDTOList = new ArrayList<>();
        for (CartEntity cartEntity : cartEntityList) {
            CartDTO dto = cartEntity.toDTO();
            cartDTOList.add(dto);
        }
        return  cartDTOList;
    }
}
