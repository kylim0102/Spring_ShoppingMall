package lhc.portfolio_test.entity;

import jakarta.persistence.*;
import lhc.portfolio_test.Enum.OrderStatus;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "order_table")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long idx;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "memberEntity_idx")
    private MemberEntity memberEntity;

    @OneToMany(mappedBy = "orderEntity", cascade = CascadeType.ALL)
    private List<OrderProductEntity> orderProductEntities = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "deliveryEntity_idx")
    private DeliveryEntity deliveryEntity;

    private LocalDateTime orderDate;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    public void setMember(MemberEntity memberEntity) {
        this.memberEntity = memberEntity;
        memberEntity.getOrders().add(this);
    }

    public void addOrderProduct(OrderProductEntity orderProductEntity) {
        orderProductEntities.add(orderProductEntity);
        orderProductEntity.setOrder(this);
    }

    public void setDelivery(DeliveryEntity deliveryEntity) {
        this.deliveryEntity = deliveryEntity;
        deliveryEntity.setOrder(this);
    }
}
