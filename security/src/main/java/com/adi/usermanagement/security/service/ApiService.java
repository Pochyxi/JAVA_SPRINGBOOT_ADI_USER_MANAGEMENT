package com.adi.usermanagement.security.service;

import com.adi.usermanagement.security.dto.*;
import reactor.core.publisher.Mono;

import java.util.Set;

public interface ApiService {
    Mono<UserDTO> getById( Long id);
    Mono<UserDTOInternal> getUserData( String usernameOrEmail);
    Mono<Boolean> existsByUsernameOrEmail(String usernameOrEmail);
    Mono<Set<ProfilePermissionDTO>> getProfilePermissions( Long profileId);
    Mono<Void> signup( SignupDTO signupDTO );
    Mono<ProfileDTO> getProfile( Long userId );
}
