package com.adi.usermanagement.security.service.impl;

import com.adi.usermanagement.security.dto.ProfileDTO;
import com.adi.usermanagement.security.dto.ProfilePermissionDTO;
import com.adi.usermanagement.security.dto.UserDTO;
import com.adi.usermanagement.security.dto.UserDTOInternal;
import com.adi.usermanagement.security.service.ApiService;
import com.adi.usermanagement.security.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final ApiService apiService;

    @Override
    public UserDTO findById( Long id ) {
        return apiService.getById( id ).block();
    }

    @Override
    public ProfileDTO getProfile( Long userId ) {
        return apiService.getProfile( userId ).block();
    }

    /**
     * Ritorna un utente in base all'username o email
     * @param usernameOrEmail username o email
     * @return utente interno
     */
    @Override
    public Optional<UserDTOInternal> findByUsernameOrEmail( String usernameOrEmail) {
        return apiService.getUserData(usernameOrEmail)
                .map(Optional::of)
                .defaultIfEmpty(Optional.empty())
                .block();
    }

    /**
     * Verifica se esiste un utente in base all'username o email
     * @param usernameOrEmail username o email
     * @return true se esiste, false altrimenti
     */
    @Override
    public boolean existsByUsernameOrEmail( String usernameOrEmail ) {
        return Boolean.TRUE.equals( apiService.existsByUsernameOrEmail( usernameOrEmail ).block() );
    }

    /**
     * Ritorna i permessi di un profilo
     * @param profileId id del profilo
     * @return set di permessi
     */
    @Override
    public Set<ProfilePermissionDTO> getProfilePermissions( Long profileId ) {
        return apiService.getProfilePermissions( profileId ).block();
    }
}
