package com.adi.usermanagement.security.dto;

import com.adi.usermanagement.security.enumerated.PermissionList;
import lombok.Data;

@Data
public class PermissionDTO {

  private Long id;

  private PermissionList name;

}
