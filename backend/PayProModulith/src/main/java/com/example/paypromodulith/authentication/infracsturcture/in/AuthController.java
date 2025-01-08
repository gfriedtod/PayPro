package com.example.paypromodulith.authentication.infracsturcture.in;

import com.example.paypromodulith.authentication.domain.model.AdminDto;
import com.example.paypromodulith.authentication.domain.model.LoginRequest;
import com.example.paypromodulith.authentication.domain.service.AdminAuthService;
import com.example.paypromodulith.authentication.domain.service.UserAuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;

@Slf4j
@RestController
@CrossOrigin("*")
@RequestMapping("auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserAuthService userAuthService;
    private final AdminAuthService adminAuthService;

    @GetMapping("hello")
    public String hello(){
        return "hello";
    }

    @PostMapping("login/user")
    ResponseEntity<?> loginUser(@RequestBody LoginRequest loginRequest) throws NoSuchAlgorithmException {
        try{
            var res = this.userAuthService.login(loginRequest);
            if (res.isEmpty()) return ResponseEntity.notFound().build();
            return ResponseEntity.ok(res.get());

        } catch (Exception e){
            log.error("e: ", e);
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PostMapping("login")
    ResponseEntity<?> login(@RequestBody LoginRequest loginRequest){
        try{
         var res =   this.adminAuthService.login(loginRequest);
         log.info("res: {}", res);
         if(res.isPresent()){
             return  ResponseEntity.ok(res.get());
         }

         return ResponseEntity.notFound().build();

        }catch (Exception e){
            AuthController.log.error("e: ", e);
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @PostMapping("signup")
    ResponseEntity<?> singup(@RequestBody AdminDto adminDto){
        try{
            return ResponseEntity.ok(this.adminAuthService.signup(adminDto));

        } catch (Exception e){
            AuthController.log.error("e: ", e);
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


}
