package lhc.portfolio_test.dto;

import lhc.portfolio_test.entity.AddressEntity;
import lombok.*;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AddressDTO {
    private String address;
    private String addressDetail;
    private String etc;
    private String zipcode;

    public AddressEntity AddressToEntity () {
        return AddressEntity.builder()
                .address(address)
                .addressDetail(addressDetail)
                .etc(etc)
                .zipcode(zipcode)
                .build();
    }
}
