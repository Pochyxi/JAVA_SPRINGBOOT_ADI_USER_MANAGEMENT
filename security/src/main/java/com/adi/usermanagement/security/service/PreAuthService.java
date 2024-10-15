package com.adi.usermanagement.security.service;

public interface PreAuthService {

    boolean userHasPowerOnSubject(Long subjectId, String permissionName );
}
