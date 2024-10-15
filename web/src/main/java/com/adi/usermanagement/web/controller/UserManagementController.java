package com.adi.usermanagement.web.controller;

import com.adi.usermanagement.security.dto.SignupDTO;
import com.adi.usermanagement.security.dto.UserDTO;
import com.adi.usermanagement.security.service.UserManagementService;
import com.adi.usermanagement.security.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user-management")
@RequiredArgsConstructor
public class UserManagementController {

    private final UserManagementService userManagementService;
    private final UserService userService;

    /**
     * SIGNUP
     * Questo metodo permette di effettuare la registrazione di un utente
     * PREAUTHORIZATION
     * NONE
     */
    @PostMapping("/signup")
    @PreAuthorize( "hasAuthority('USER_CREATE')" )
    public void signup( @RequestBody SignupDTO signupDTO ) {
        userManagementService.signup( signupDTO );

    }

    @GetMapping("/user/{id}")
    @PreAuthorize("@authorityService.userHasPowerOnSubject(#id, 'USER_READ')")
    public ResponseEntity<UserDTO> getUserById( @PathVariable("id") Long id ) {
        return ResponseEntity.ok( userService.findById( id ) );
    }
}
