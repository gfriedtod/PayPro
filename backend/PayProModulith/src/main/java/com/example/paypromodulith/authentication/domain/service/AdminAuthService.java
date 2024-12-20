package com.example.paypromodulith.authentication.domain.service;

import com.example.paypromodulith.authentication.application.input.AdminAuthInput;
import com.example.paypromodulith.authentication.application.output.AdminOutputPort;
import com.example.paypromodulith.authentication.domain.model.*;
import com.example.paypromodulith.authentication.infracsturcture.out.jwt.JwtHelper;
import com.example.paypromodulith.mail.domain.dto.EmailRequest;
import com.example.paypromodulith.mail.domain.service.MaileService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class AdminAuthService implements AdminAuthInput {

    private final AdminOutputPort adminOutputPort;
    private final PasswordEncoder passwordEncoder;
    private final MaileService maileService;

    public Optional<LoginResponse> login(LoginRequest request) throws NoSuchAlgorithmException {
        AdminDto admin = adminOutputPort.loadUserByUsername(request.getEmail());
        if (passwordEncoder.matches(request.getPassword(), admin.getPassword())) {
             String token = JwtHelper.generateToken(new TokenDetails(admin.getId(), admin.getEmail()));
            return Optional.ofNullable(LoginResponse.builder().user(admin).token(token).build());
        }
        return Optional.empty();
    }



    @Override
    public SignupResponse signup(AdminDto adminDto) {
        var password = adminDto.getPassword();
        adminDto.setPassword(passwordEncoder.encode(password));
        try {
            var admin = adminOutputPort.signup(adminDto);
            maileService.send(
                    EmailRequest.builder()
                            .to(admin.getAdminDto().getEmail())
                            .subject("Welcome to Paypro")
                            .message("<h1>Welcome to Paypro</h1>\n" +
                                    "\n" +
                                    "<p>Hi " + admin.getAdminDto().getName() + "</p>\n" +
                                    "\n" +
                                    "<p>email: " + admin.getAdminDto().getEmail() + "</p>\n" +
                                    "\n" +
                                    "<p>password: " + password + "</p>\n" +
                                    "<p>Thank you for registering with us. We are excited to have you join our community!</p>\n" +
                                    "\n" ).build()

            );

            return admin;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
