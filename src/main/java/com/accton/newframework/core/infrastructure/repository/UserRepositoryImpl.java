package com.accton.newframework.core.infrastructure.repository;

import com.accton.newframework.core.domain.identity.UserRepository;
import com.accton.newframework.core.domain.identity.model.RoleModel;
import com.accton.newframework.core.domain.identity.model.UserModel;
import com.accton.newframework.core.infrastructure.dao.RoleDao;
import com.accton.newframework.core.infrastructure.dao.UserDao;
import com.accton.newframework.core.infrastructure.entities.RoleEntity;
import com.accton.newframework.core.infrastructure.entities.UserEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserRepositoryImpl implements UserRepository {

    private final UserDao userDao;
    private final RoleDao roleDao;

    public UserRepositoryImpl(UserDao userDao,
                              RoleDao roleDao) {
        this.userDao = userDao;
        this.roleDao = roleDao;
    }


    @Override
    public UserModel add(UserModel user) {
        user.setUserName(user.getUserName());
        Set<RoleEntity> roles;

        if (user.getRoles() != null) {
            roles = user
                    .getRoles()
                    .stream()
                    .map(roleModel -> roleDao.findRoleEntitiesByCode(roleModel.getCode()))
                    .filter(Optional::isPresent)
                    .map(Optional::get)
                    .collect(Collectors.toSet());
        } else {
            roles = new HashSet<>();
            roleDao.findRoleEntitiesByCode("user").ifPresent(roles::add);
        }

        userDao.save(UserEntity.builder()
                .userName(user.getUserName())
                .displayName(user.getDisplayName())
                .password(user.getPassword())
                .roles(roles)
                .build());
        return null;
    }

    @Override
    public UserModel getById(Long id) {
        return null;
    }

    @Override
    public void update(UserModel user) {

    }


    @Override
    public UserModel getByUserName(String userName) {
        Optional<UserEntity> entity = userDao.findOneWithRolesByUserName(userName);
        return entity.map(userEntity ->
                        UserModel.builder()
                                .userName(userEntity.getUserName())
                                .displayName(userEntity.getDisplayName())
                                .roles(userEntity.getRoles().stream().map(roleEntity ->
                                                new RoleModel(roleEntity.getCode(),
                                                        roleEntity.getDescription()))
                                        .collect(Collectors.toSet()))
                                .build())
                .orElse(null);
    }

    @Transactional
    @Override
    public void addRoles(Set<RoleModel> roles) {
        Iterable<RoleEntity> iterator = roles.stream().map(roleModel ->
                RoleEntity.builder()
                        .code(roleModel.getCode())
                        .description(roleModel.getDescription())
                        .build()).collect(Collectors.toList());
        roleDao.saveAll(iterator);
    }
}
