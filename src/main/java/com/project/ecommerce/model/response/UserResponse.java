package com.project.ecommerce.model.response;


import com.project.ecommerce.enums.RoleCode;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponse {

    private Long id;

    private String username;

    private String fullName;

    private String email;

    private RoleCode role;
}
