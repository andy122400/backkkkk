package com.accton.newframework.core.domain.identity.model;

import lombok.*;

import java.util.Set;

@Setter @Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserModel {

    private Long userId;
    private String userName;
    private String displayName;
    private String password;
    private Set<RoleModel> roles;

}
