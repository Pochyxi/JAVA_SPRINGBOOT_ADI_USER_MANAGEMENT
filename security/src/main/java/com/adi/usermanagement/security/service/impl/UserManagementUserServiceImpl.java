package com.adi.usermanagement.security.service.impl;

import com.adi.usermanagement.security.dto.SignupDTO;
import com.adi.usermanagement.security.service.ApiService;
import com.adi.usermanagement.security.service.UserManagementService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserManagementUserServiceImpl implements UserManagementService {

    private final ApiService apiService;

    @Override
    public void signup( SignupDTO signupDTO ) {
        this.apiService.signup( signupDTO ).block();
    }
}
