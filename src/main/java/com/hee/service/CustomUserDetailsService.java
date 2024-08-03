package com.hee.service;

import com.hee.model.USER_ROLE;
import com.hee.model.User;
import com.hee.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {

//   Inject Repository vào bằng cách sử dụng @Autowired
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Lấy ra từ Repository User là Entity
        User user = userRepository.findByEmail(username);

        if(user == null){
            throw new UsernameNotFoundException("User not found with email" + username);
        }

        USER_ROLE role = user.getRole();

        if(role==null){
            role = USER_ROLE.ROLE_CUSTOMER;
        }

//        Tạo một danh sách các quyền hạn (authorities) cho người dùng
        List<GrantedAuthority> authorities = new ArrayList<>();

//        Thêm quyền hạn dựa trên vai trò của người dùng vào danh sách.
//        Biển đổi Entity thành Model
        authorities.add(new SimpleGrantedAuthority(role.toString()));

        return new org.springframework.security.core.userdetails.User(user.getEmail(),user.getPassword(),authorities);
    }
}
