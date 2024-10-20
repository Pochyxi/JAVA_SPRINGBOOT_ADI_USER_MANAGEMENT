package com.adi.usermanagement.web.controller;

import com.adi.usermanagement.security.dto.ChangePasswordDTO;
import com.adi.usermanagement.security.dto.SignupDTO;
import com.adi.usermanagement.security.dto.UserDTO;
import com.adi.usermanagement.security.enumerated.TokenType;
import com.adi.usermanagement.security.service.UserManagementService;
import jakarta.validation.Valid;
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

    @PostMapping("/create")
    @PreAuthorize( "hasAuthority('USER_CREATE')" )
    public ResponseEntity<UserDTO> createUser( @Valid @RequestBody SignupDTO signupDTO) {
        UserDTO userDTO = userManagementService.createUser( signupDTO );

        return new ResponseEntity<>( userDTO, HttpStatus.OK );
    }

    @GetMapping(value = "/findByEmail/{email}")
    @PreAuthorize( "hasAuthority('USER_READ')" )
    public ResponseEntity<UserDTO> findByEmail( @PathVariable("email") String email) {
        UserDTO userDTO = userManagementService.findByEmail( email );

        return new ResponseEntity<>( userDTO, HttpStatus.OK );
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

    /**
     * CHANGE PASSWORD
     * Questo metodo permette di modificare la password
     * PREAUTHORIZATION:
     * 1 - E' possibile cambiare la password avendo a disposizione il token inviato tramite email
     * 2 - Attraverso autenticazione specificando username e vecchia password
     */
    @PutMapping(value = "/change_password")
    public ResponseEntity<Void> changePassword(@Valid @RequestBody ChangePasswordDTO changePasswordDTO,
                                               @RequestParam(value = "token", required = false) String token ) {
        userManagementService.changePassword( changePasswordDTO, token );

        return new ResponseEntity<>( HttpStatus.OK );
    }

    /**
     * CHANGE EMAIL
     * Questo metodo permette di modificare l'email
     * PREAUTHORIZATION:
     * Utente con permesso USER_UPDATE
     */
    @PutMapping(value = "/change_email")
    @PreAuthorize("hasAuthority('USER_UPDATE')")
    public ResponseEntity<Void> changeEmail(@RequestParam Long userId,
                                            @RequestParam String email){
        userManagementService.changeEmail( userId, email );

        return new ResponseEntity<>( HttpStatus.OK );
    }

    @GetMapping(value = "/recovery_password")
    public ResponseEntity<Void> recovery(@RequestParam("email") String email) {
        userManagementService.recoveryPassword(email);

        return new ResponseEntity<> (HttpStatus.OK);
    }

    /**
     * RESEND VERIFICATION
     * Questo metodo permette di inviare nuovamente la richiesta di verifica
     * PREAUTHORIZATION:
     * Utente con permesso USER_UPDATE
     */
    @PutMapping(value = "/resend_verification")
    @PreAuthorize("hasAuthority('USER_UPDATE')")
    public ResponseEntity<Void> resendVerification(@RequestParam Long userId) {
        userManagementService.resendVerificationRequest(userId);
        return new ResponseEntity<> (HttpStatus.OK);
    }

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
}
