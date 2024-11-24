package com.example.paypromodulith.authentication.domain.service;

import com.example.paypromodulith.authentication.application.output.AdminOutputPort;
import com.example.paypromodulith.authentication.application.output.UserOutputPort;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
public class ClientService implements UserDetailsService  {
    private final AdminOutputPort adminOutputPort;
    private final UserOutputPort userOutputPort;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println(username);
        var admin = adminOutputPort.loadUserByUsername(username);
        var user = userOutputPort.loadUserByUsername(username);
        if (admin != null){
            System.out.println(admin.getEmail());
            return User.builder().username(admin.getEmail()).password(admin.getPassword()).build();
        } else if (user != null) {
            return User.builder().username(user.getEmail()).build();
        }

        return null;
    }


}
