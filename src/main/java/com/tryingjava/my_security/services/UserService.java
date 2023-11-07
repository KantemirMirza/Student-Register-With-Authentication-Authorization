package com.tryingjava.my_security.services;

import com.tryingjava.my_security.DTO.UserDTO;
import com.tryingjava.my_security.models.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    User saveUser(UserDTO userDTO);

}
