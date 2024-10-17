package com.adi.usermanagement.security.service;

import com.adi.usermanagement.security.dto.ChangePasswordDTO;
import com.adi.usermanagement.security.dto.SignupDTO;
import com.adi.usermanagement.security.enumerated.TokenType;

public interface UserManagementService {

    void signup( SignupDTO signupDTO);

    void verifyToken( String token, TokenType tokenType );

    void changePassword( ChangePasswordDTO changePasswordDTO, String token );

    void changeEmail( Long userId, String email );

    void recoveryPassword( String email );

    void resendVerificationRequest( Long userId );
}
