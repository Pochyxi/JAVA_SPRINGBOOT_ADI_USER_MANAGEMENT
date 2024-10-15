package com.adi.usermanagement.security.service;

import com.adi.usermanagement.security.dto.ProfileDTO;
import com.adi.usermanagement.security.dto.ProfilePermissionDTO;
import com.adi.usermanagement.security.dto.UserDTO;
import com.adi.usermanagement.security.dto.UserDTOInternal;

import java.util.Optional;
import java.util.Set;

public interface UserService {

    UserDTO findById( Long id );

    Optional<UserDTOInternal> findByUsernameOrEmail( String usernameOrEmail);

    boolean existsByUsernameOrEmail( String usernameOrEmail );

    Set<ProfilePermissionDTO> getProfilePermissions( Long profileId );

    ProfileDTO getProfile( Long userId );
}
