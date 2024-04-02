package lhc.portfolio_test.dto;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

public class CustomUserDetails implements UserDetails {

    private String Username;
    private String Password;
    private String role;
    private String name;

    public CustomUserDetails(String username, String password, String role, String name) {
        this.Username = username;
        this.name = name;
        this.Password = password;
        this.role = role;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collection = new ArrayList<>();
        collection.add(new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return role;
            }
        });

        return collection;
    }

    @Override
    public String getPassword() {
        return Password;
    }

    @Override
    public String getUsername() {
        return Username;
    }

    @Override
    public boolean isAccountNonExpired() {  //계정 만료 여부
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {   //계정 잠김 여부
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {  //비밀번호 만료 여부
        return true;
    }

    @Override
    public boolean isEnabled() {    //사용자 활성화 여부
        return true;
    }

    public String getName() {
        return name;
    }
}
