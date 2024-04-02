package lhc.portfolio_test.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lhc.portfolio_test.dto.AddressDTO;
import lombok.Builder;

@Embeddable
public class AddressEntity {

    @Column(name = "address")
    private String address;
    @Column(name="addressDetail")
    private String addressDetail;
    @Column(name = "etc")
    private String etc;
    @Column(name = "zipcode")
    private String zipcode;

    protected AddressEntity() {
    }

    @Builder
    public AddressEntity(String address, String addressDetail, String etc, String zipcode) {
        this.address = address;
        this.addressDetail = addressDetail;
        this.etc = etc;
        this.zipcode = zipcode;
    }

    public AddressDTO AddressToDTO() {
        return AddressDTO.builder()
                .address(address)
                .addressDetail(addressDetail)
                .etc(etc)
                .zipcode(zipcode)
                .build();
    }

    public String getAddress() {
        return this.address;
    }

    public String getAddressDetail() {
        return this.addressDetail;
    }

    public String getEtc() {
        return this.etc;
    }

    public String getZipcode() {
        return this.zipcode;
    }
}
