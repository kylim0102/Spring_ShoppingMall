package lhc.portfolio_test.service;

import lhc.portfolio_test.dto.MemberDTO;
import lhc.portfolio_test.dto.MyPageDTO;
import lhc.portfolio_test.dto.RegisterDTO;
import lhc.portfolio_test.entity.MemberEntity;
import lhc.portfolio_test.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MemberService {
    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void Signup (RegisterDTO memberDTO) {
        boolean isUser = memberRepository.existsByUsername(memberDTO.getId());
        if (!isUser) {
            memberDTO.setPassword(passwordEncoder.encode(memberDTO.getPassword()));
            memberRepository.save(new MemberEntity(memberDTO));
        }
    }

    public boolean idCheck(String id) {
        return memberRepository.existsByUsername(id);
    }

    public MyPageDTO findByUserid(String username) {
        MemberEntity byUsername = memberRepository.findByUsername(username);

        MyPageDTO dto = byUsername.toMyPageDTO();

        return dto;

    }

    public List<MemberDTO> findAll() {
        List<MemberEntity> memberEntityList = memberRepository.findAll(Sort.by(Sort.Direction.DESC, "idx"));

        List<MemberDTO> memberDTOList = new ArrayList<>();
        for (MemberEntity memberEntity : memberEntityList) {
            MemberDTO dto = memberEntity.toDTO();
            memberDTOList.add(dto);
        }
        return memberDTOList;
    }
}
