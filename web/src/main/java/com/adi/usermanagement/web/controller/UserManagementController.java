package com.adi.usermanagement.web.controller;

import com.adi.usermanagement.security.dto.SignupDTO;
import com.adi.usermanagement.security.enumerated.TokenType;
import com.adi.usermanagement.security.service.UserManagementService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user-management/auth")
@RequiredArgsConstructor
public class UserManagementController {

    private final UserManagementService userManagementService;


    /**
     * VERIFY TOKEN
        * Questo metodo permette di verificare un token
     * PREAUTHORIZATION:
        * NONE
     */
    @GetMapping(value = "/verify")
    public ResponseEntity<Void> confirm(@RequestParam("token") String token,
                                        @RequestParam("tokentype")String tokentype) {
        userManagementService.verifyToken( token, TokenType.valueOf(tokentype));

        return new ResponseEntity<>( HttpStatus.OK );
    }

    /**
     * SIGNUP
     * Questo metodo permette di effettuare la registrazione di un utente
     * PREAUTHORIZATION
     * USER_CREATE
     */
    @PostMapping("/signup")
    @PreAuthorize( "hasAuthority('USER_CREATE')" )
    public void signup( @RequestBody SignupDTO signupDTO ) {
        userManagementService.signup( signupDTO );

    }
}
