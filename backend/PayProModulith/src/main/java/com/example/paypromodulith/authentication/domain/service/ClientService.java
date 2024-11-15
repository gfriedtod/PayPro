package com.example.paypromodulith.authentication.domain.service;

import com.example.paypromodulith.authentication.application.input.ClientInput;
import com.example.paypromodulith.authentication.application.output.AdminOutputPort;
import com.example.paypromodulith.authentication.application.output.ClientOutput;
import com.example.paypromodulith.authentication.application.output.UserOutputPort;
import com.example.paypromodulith.authentication.domain.model.ClientDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
public class ClientService implements UserDetailsService , ClientInput {
    private final AdminOutputPort adminOutputPort;
    private final UserOutputPort userOutputPort;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        var admin = adminOutputPort.loadUserByUsername(username);
        var user = userOutputPort.loadUserByUsername(username);
        if (admin != null) {
            return User.builder().username(admin.getName()).build();
        } else if (user != null) {
            return User.builder().username(user.getName()).build();
        }

        return null;
    }

    @Override
    public ClientDto ambiguousSearch(String name) {
        return null;
    }
}
