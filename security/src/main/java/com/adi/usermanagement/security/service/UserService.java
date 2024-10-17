package com.adi.usermanagement.security.service;

import com.adi.usermanagement.security.dto.*;

import java.util.Optional;
import java.util.Set;

public interface UserService {

    UserDTO findById( Long id );

    Optional<UserDTOInternal> findByUsernameOrEmail( String usernameOrEmail);

    Set<ProfilePermissionDTO> getProfilePermissions( Long profileId );

    ProfileDTO getProfile( Long userId );

    boolean existsByUsernameOrEmail( String usernameOrEmail );

    PagedResponseDTO<UserDTO> getAllUsers(int pageNo, int pageSize, String sortBy, String sortDir);
    PagedResponseDTO<UserDTO> getUsersByEmailContainsIgnoreCase(String email, int pageNo, int pageSize, String sortBy,
                                                                String sortDir);

    UserDTOInternal getUserByAuthentication();

    UserDTO modifyUser( Long id, UserDTO userDTO );

    void deleteUser( Long id );
}
