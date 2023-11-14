package com.accton.newframework.core.infrastructure.mapper;

import com.accton.newframework.core.domain.identity.model.RoleModel;
import com.accton.newframework.core.domain.identity.model.UserModel;
import com.accton.newframework.core.infrastructure.entities.UserEntity;
import lombok.experimental.UtilityClass;

import java.util.stream.Collectors;

@UtilityClass
public class UserMapper {

    public UserModel toDomainModel(UserEntity userEntity) {
        return UserModel.builder()
                .userId(userEntity.getId())
                .userName(userEntity.getUserName())
                .displayName(userEntity.getDisplayName())
                .password(userEntity.getPassword())
                .roles(userEntity.getRoles().stream().map(roleEntity ->
                                new RoleModel(roleEntity.getCode(),
                                        roleEntity.getDescription()))
                        .collect(Collectors.toSet()))
                .build();
    }
}
