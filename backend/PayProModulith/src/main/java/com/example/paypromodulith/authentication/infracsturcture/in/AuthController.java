package com.example.paypromodulith.authentication.infracsturcture.in;

import com.example.paypromodulith.authentication.application.input.AdminAuthInput;
import com.example.paypromodulith.authentication.domain.model.AdminDto;
import com.example.paypromodulith.authentication.domain.model.LoginRequest;
import com.example.paypromodulith.authentication.domain.service.AdminAuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@CrossOrigin("*")
@RequestMapping("auth")
@RequiredArgsConstructor
public class AuthController {

    private final AdminAuthService adminAuthService;

    @GetMapping("hello")
    public String hello(){
        return "hello";
    }
    @PostMapping("login")
    ResponseEntity<?> login(@RequestBody LoginRequest loginRequest){
        try{
         var res =   this.adminAuthService.login(loginRequest);

         if(res.isPresent()){
             return  ResponseEntity.ok(res.get());
         }

         return ResponseEntity.notFound().build();

        }catch (Exception e){
            log.error("e: ", e);
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @PostMapping("singup")
    ResponseEntity<?> singup(@RequestBody AdminDto adminDto){
        try{
            return ResponseEntity.ok(this.adminAuthService.signup(adminDto));

        } catch (Exception e){
            log.error("e: ", e);
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


}
