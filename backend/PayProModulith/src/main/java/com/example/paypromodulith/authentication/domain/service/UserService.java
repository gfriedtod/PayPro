package com.example.paypromodulith.authentication.domain.service;

import com.example.paypromodulith.authentication.application.output.UserOutputPort;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserOutputPort userOutputPort;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        return userOutputPort.loadUserByUsername(username);
        return null;
    }
}
