package lhc.portfolio_test.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RegisterDTO {

    private String id;
    private String password;
    private String name;
    private String f_rrn;   //주민 앞자리
    private String b_rrn;   //주민 뒷자리
    private String f_phone; //휴대폰 앞자리
    private String m_phone; //휴대폰 중간
    private String e_phone; //휴대폰 마지막
    private String mail;
    private String web;
    private int cash;
    private String address;
    private String addressDetail;
    private String etc;
    private String zipcode;


}
