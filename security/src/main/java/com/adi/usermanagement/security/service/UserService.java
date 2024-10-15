package com.adi.usermanagement.security.service;

import com.adi.usermanagement.security.dto.ProfilePermissionDTO;
import com.adi.usermanagement.security.dto.UserDTOInternal;

import java.util.Optional;
import java.util.Set;

public interface UserService {

    Optional<UserDTOInternal> findByUsernameOrEmail( String usernameOrEmail);

    boolean existsByUsernameOrEmail( String usernameOrEmail );

    Set<ProfilePermissionDTO> getProfilePermissions( Long profileId );
}
