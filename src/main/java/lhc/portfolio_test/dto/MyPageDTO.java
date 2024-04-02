package lhc.portfolio_test.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MyPageDTO {

    private String id;
    private String name;
    private String phone;
    private String email;
    private int cash;
}
