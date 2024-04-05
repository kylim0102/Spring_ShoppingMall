package lhc.portfolio_test.service;

import lhc.portfolio_test.dto.CartDTO;
import lhc.portfolio_test.entity.CartEntity;
import lhc.portfolio_test.entity.ProductEntity;
import lhc.portfolio_test.repository.CartRepository;
import lhc.portfolio_test.repository.ProductRepository;
import lhc.portfolio_test.repository.ProductRepositoryCustom;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartService {

    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final ProductRepositoryCustom productRepositoryCustom;

    public CartService(CartRepository cartRepository, ProductRepository productRepository, ProductRepositoryCustom productRepositoryCustom) {
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
        this.productRepositoryCustom = productRepositoryCustom;
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
            Hibernate.initialize(cartEntity.getCartProducts()); // ProductEntity 초기화
            Hibernate.initialize(cartEntity.getCartProducts().getProductImgEntityList());
            CartDTO dto = cartEntity.toDTO();
            cartDTOList.add(dto);
        }
        return cartDTOList;
    }

    @Transactional
    public List<CartEntity> findCart() {
        return productRepositoryCustom.findAllWithProduct();
    }
}
