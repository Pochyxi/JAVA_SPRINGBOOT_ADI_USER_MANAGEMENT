package com.adi.usermanagement.security.service;

import com.adi.usermanagement.security.dto.*;
import com.adi.usermanagement.security.enumerated.TokenType;
import reactor.core.publisher.Mono;

import java.util.Set;

public interface UserApiService {
    Mono<UserDTO> getById( Long id);
    Mono<UserDTOInternal> getUserData( String usernameOrEmail);
    Mono<Boolean> existsByUsernameOrEmail(String usernameOrEmail);
    Mono<Set<ProfilePermissionDTO>> getProfilePermissions( Long profileId);
    Mono<Void> signup( SignupDTO signupDTO );
    Mono<ProfileDTO> getProfile( Long userId );
    Mono<PagedResponseDTO<UserDTO>> getAllUsers(int pageNo, int pageSize, String sortBy, String sortDir);
    Mono<PagedResponseDTO<UserDTO>> getUsersByEmailContainsIgnoreCase(String email, int pageNo, int pageSize,
                                                                      String sortBy,
                                                                      String sortDir);
    Mono<UserDTO> modifyUser( Long id, UserDTO userDTO );
    Mono<Void> deleteUser( Long id );
    Mono<Void> verifyToken( String token, TokenType tokenType );
}
