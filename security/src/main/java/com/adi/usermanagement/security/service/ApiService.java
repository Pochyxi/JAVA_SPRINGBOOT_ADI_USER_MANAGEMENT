package com.adi.usermanagement.security.service;

import com.adi.usermanagement.security.dto.ProfilePermissionDTO;
import com.adi.usermanagement.security.dto.UserDTOInternal;
import reactor.core.publisher.Mono;

import java.util.Set;

public interface ApiService {
    Mono<UserDTOInternal> getUserData( String usernameOrEmail);
    Mono<Boolean> existsByUsernameOrEmail(String usernameOrEmail);
    Mono<Set<ProfilePermissionDTO>> getProfilePermissions( Long profileId);
}
