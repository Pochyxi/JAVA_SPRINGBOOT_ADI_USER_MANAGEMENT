package com.adi.usermanagement.security.service.impl;

import com.adi.usermanagement.security.dto.SignupDTO;
import com.adi.usermanagement.security.service.UserApiService;
import com.adi.usermanagement.security.service.UserManagementService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserManagementUserServiceImpl implements UserManagementService {

    private final UserApiService userApiService;

    @Override
    public void signup( SignupDTO signupDTO ) {
        this.userApiService.signup( signupDTO ).block();
    }
}
