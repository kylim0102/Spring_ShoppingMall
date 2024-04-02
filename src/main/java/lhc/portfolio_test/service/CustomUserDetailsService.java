package lhc.portfolio_test.service;

import lhc.portfolio_test.dto.CustomUserDetails;
import lhc.portfolio_test.entity.MemberEntity;
import lhc.portfolio_test.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        MemberEntity userData = memberRepository.findByUsername(name);
        if(userData != null) {
            return new CustomUserDetails(userData.getUsername(), userData.getPassword(), userData.getRole(), userData.getName());
        } else {
            throw new UsernameNotFoundException("");
        }
    }
}
