package lhc.portfolio_test.entity;

import jakarta.persistence.*;
import lhc.portfolio_test.Enum.DeliveryStatus;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "delivery")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DeliveryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long idx;

    @OneToOne(mappedBy = "deliveryEntity", fetch = FetchType.LAZY)
    private OrderEntity orderEntity;

    @Embedded
    private AddressEntity addressEntity;

    @Enumerated(EnumType.STRING)
    private DeliveryStatus status;

    public void setOrder(OrderEntity orderEntity) {
        this.orderEntity = orderEntity;
    }
}
