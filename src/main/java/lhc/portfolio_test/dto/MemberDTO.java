package lhc.portfolio_test.dto;

import lombok.*;

import java.time.LocalDate;

@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class MemberDTO {
    private Long idx;
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
    private String role;
    private int cash;
    private LocalDate regdate;
    private AddressDTO addressDTO;
}

