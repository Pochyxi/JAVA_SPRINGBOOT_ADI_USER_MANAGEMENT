package com.adi.usermanagement.web.controller;

import com.adi.usermanagement.security.dto.PagedResponseDTO;
import com.adi.usermanagement.security.dto.SignupDTO;
import com.adi.usermanagement.security.dto.UserDTO;
import com.adi.usermanagement.security.service.UserManagementService;
import com.adi.usermanagement.security.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user-management/user")
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

    /**
     * GET USER BY ID
     * Questo metodo permette di ottenere i dati di un utente specificando l'id
     * PREAUTHORIZE:
     * Utente con permesso USER_READ e potere più alto sull'utente specificato
     */
    @GetMapping("/{id}")
    @PreAuthorize("@authorityService.userHasPowerOnSubject(#id, 'USER_READ')")
    public ResponseEntity<UserDTO> getUserById( @PathVariable("id") Long id ) {
        return ResponseEntity.ok( userService.findById( id ) );
    }

    /*
     * GET ALL USERS
     * Questo metodo permette di ottenere tutti gli utenti
     * PREAUTHORIZE:
     * Utente con permesso USER_READ e potere più alto degli utenti richiesti
     */
    @GetMapping(value = "/all")
    @PreAuthorize("hasAuthority('USER_READ')")
    public ResponseEntity<PagedResponseDTO<UserDTO>> getAllUsers(
            @RequestParam(value = "pageNo", defaultValue = "${app.pagination.default_pageNumber}") int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "${app.pagination.default_pageSize}") int pageSize,
            @RequestParam(value = "sortBy", defaultValue = "${app.pagination.default_sortBy}", required = false) String sortBy,
            @RequestParam(value = "sortOrder", defaultValue = "${app.pagination.default_sortDirection}") String sortDir
    ) {

        return new ResponseEntity<>( userService.getAllUsers( pageNo, pageSize, sortBy, sortDir ), HttpStatus.OK );
    }

    /**
     * MODIFICA UTENTE
     * Questo metodo permette di modificare i dati di un utente specificando l'id
     * PREAUTHORIZE:
     * Utente con permesso USER_UPDATE e potere più alto sull'utente specificato
     */
    @PutMapping("/update/{id}")
    @PreAuthorize("@authorityService.userHasPowerOnSubject(#id, 'USER_UPDATE')")
    public ResponseEntity<UserDTO> modifyUser( @PathVariable("id") Long id,
                                               @RequestBody UserDTO userDTO ) {

        return new ResponseEntity<>( userService.modifyUser( id, userDTO ), HttpStatus.OK );
    }

    /**
     * ELIMINA UTENTE
     * Questo metodo permette di eliminare un utente specificando l'id
     * PREAUTHORIZE:
     * Utente con permesso USER_DELETE e potere più alto sull'utente specificato
     */
    @DeleteMapping("/delete/{id}")
    @PreAuthorize("@authorityService.userHasPowerOnSubject(#id, 'USER_DELETE')")
    public ResponseEntity<Void> deleteUser( @PathVariable("id") Long id ) {
        userService.deleteUser( id );

        return new ResponseEntity<>( HttpStatus.OK );
    }
}
