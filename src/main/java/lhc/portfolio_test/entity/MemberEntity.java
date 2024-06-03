package lhc.portfolio_test.entity;

import jakarta.persistence.*;
import lhc.portfolio_test.dto.AddressDTO;
import lhc.portfolio_test.dto.MemberDTO;
import lhc.portfolio_test.dto.MyPageDTO;
import lhc.portfolio_test.dto.RegisterDTO;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Table(name = "member_table")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @Setter
    @Column (name="id", length = 250, nullable = false)
    private String username;

    @Setter
    @Column (length = 250, nullable = false)
    private String password;

    @Setter
    @Column (length = 250, nullable = false)
    private String name;

    @Setter
    @Column (length = 250, nullable = false)
    private String rrn;

    @Setter
    @Column (length = 250, nullable = false)
    private String phone;

    @Setter
    @Column (length = 250, nullable = false)
    private String email;

    @Embedded
    private AddressEntity addressEntity;

    @Setter
    @Column (length = 250)
    private String role;

    @Setter
    @Column (length = 250)
    private int cash;

    @OneToMany(mappedBy = "memberEntity")
    private List<OrderEntity> orders = new ArrayList<>();

    @CreationTimestamp
    private LocalDateTime regdate;

    @Builder
    public MemberEntity(String role, String id, String password, String name, String rrn, String phone, String email, int cash, AddressEntity addressEntity) {
        this.role = role;
        this.username = id;
        this.password = password;
        this.name = name;
        this.rrn = rrn;
        this.phone = phone;
        this.email = email;
        this.cash = cash;
        this.addressEntity = addressEntity;
    }

    public MemberDTO toDTO() {
        return MemberDTO.builder()
                .idx(idx)
                .role(role)
                .id(username)
                .password(password)
                .name(name)
                .f_rrn(rrn.split("-")[0])
                .b_rrn(rrn.split("-")[1])
                .f_phone(phone.split("-")[0])
                .m_phone(phone.split("-")[1])
                .e_phone(phone.split("-")[2])
                .mail(email.split("@")[0])
                .web(email.split("@")[1])
                .addressDTO(AddressDTO.builder()
                        .address(addressEntity.getAddress())
                        .addressDetail(addressEntity.getAddressDetail())
                        .etc(addressEntity.getEtc())
                        .zipcode(addressEntity.getZipcode())
                        .build())
                .cash(0)
                .build();
    }

    public MyPageDTO toMyPageDTO() {
        return MyPageDTO.builder()
                .id(username)
                .name(name)
                .phone(phone)
                .email(email)
                .cash(cash)
                .build();
    }


    public MemberEntity(RegisterDTO registerDTO) {
        this.username = registerDTO.getId();
        this.password = registerDTO.getPassword();
        this.name = registerDTO.getName();
        this.rrn = registerDTO.getF_rrn() + "-" + registerDTO.getB_rrn(); //더하기
        this.phone = registerDTO.getF_phone() + "-" + registerDTO.getM_phone() + "-" + registerDTO.getE_phone(); //더하기
        this.email = registerDTO.getMail() + "@" + registerDTO.getWeb(); //더하기
        this.addressEntity = AddressEntity.builder()
                .address(registerDTO.getAddress())
                .addressDetail(registerDTO.getAddressDetail())
                .etc(registerDTO.getEtc())
                .zipcode(registerDTO.getZipcode())
                .build();
        this.role = "ROLE_ADMIN";
        this.cash = registerDTO.getCash();
        this.regdate = LocalDateTime.now();
    }


}
