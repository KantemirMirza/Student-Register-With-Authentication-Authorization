package com.tryingjava.my_security.services;

import com.tryingjava.my_security.DTO.UserDTO;
import com.tryingjava.my_security.models.Role;
import com.tryingjava.my_security.models.User;
import com.tryingjava.my_security.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService{
    private UserRepository userRepositories;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepositories) {
        super();
        this.userRepositories = userRepositories;
    }
    @Override
    public User saveUser(UserDTO userDTO) {
        User user = new User(userDTO.getFirstName(),
                             userDTO.getLastName(),
                             userDTO.getEmail(),
                             passwordEncoder.encode(userDTO.getPassword()),
                             Arrays.asList(new Role("ROLE_USER")));
                             return userRepositories.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepositories.findByEmail(username);
        if(user == null){
            throw new UsernameNotFoundException("Invalid Email or Password");
        }
        return new org.springframework.security.core.userdetails.User(user.getEmail(),
                user.getPassword(),mapRolesToAuthority(user.getRoles()));
    }
    private Collection<? extends GrantedAuthority>mapRolesToAuthority(Collection<Role> roles){
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getRoleName())).collect(Collectors.toList());
    }
}
