package com.example.paypromodulith.userManager.domain.service;

import com.example.paypromodulith.mail.domain.dto.EmailRequest;
import com.example.paypromodulith.mail.domain.service.MaileService;
import com.example.paypromodulith.userManager.application.in.UserUseCase;
import com.example.paypromodulith.userManager.application.out.UserOutputPort;
import com.example.paypromodulith.userManager.domain.model.OrganisationDto;
import com.example.paypromodulith.userManager.domain.model.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class UserService implements UserUseCase {

    private final UserOutputPort userOutputPort;
    private final MaileService maileService;
    @Override
    public List<UserDto> findAll() {
        return userOutputPort.findAll();
    }

    @Override
    public UserDto create(UserDto userDto) {
        try{
            String password = userDto.getPassword();
            var user  = userOutputPort.create(userDto);
            System.out.println(userDto.getPassword());

            maileService.send(
                    EmailRequest.builder()
                            .to(user.getEmail())
                            .subject("Welcome to Paypro")
                            .message("<h1>Welcome to Paypro</h1>\n" +
                                    "\n" +
                                    "<p>Hi " + user.getName() + "</p>\n" +
                                    "\n" +
                                    "<p>email: " + user.getEmail()+ "</p>\n" +
                                    "\n" +
                                    "<p>password: " + password + "</p>\n" +
                                    "<p>Thank you for registering with us. We are excited to have you join our community!</p>\n" +
                                    "\n" ).build()

            );
           return user;
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(UUID userId) {

    }

    @Override
    public UserDto update(UserDto userDto) {
        return userOutputPort.update(userDto);
    }

    @Override
    public List<UserDto> findAllByOrganisation(UUID organisation, UUID adminId) {
        return userOutputPort.findAllByOrganisation(organisation, adminId);
    }
}
