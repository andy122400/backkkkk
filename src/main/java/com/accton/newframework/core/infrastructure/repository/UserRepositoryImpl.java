package com.accton.newframework.core.infrastructure.repository;

import com.accton.newframework.core.application.logging.FrLoggable;
import com.accton.newframework.core.domain.identity.UserRepository;
import com.accton.newframework.core.domain.identity.model.RoleModel;
import com.accton.newframework.core.domain.identity.model.UserModel;
import com.accton.newframework.core.infrastructure.dao.RoleDao;
import com.accton.newframework.core.infrastructure.dao.UserDao;
import com.accton.newframework.core.infrastructure.entities.RoleEntity;
import com.accton.newframework.core.infrastructure.entities.UserEntity;
import com.accton.newframework.core.infrastructure.mapper.UserMapper;
import com.accton.newframework.utility.contants.RoleConstant;
import com.accton.newframework.utility.contants.CompanyEnum;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.ldap.core.AttributesMapper;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.support.LdapUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.naming.NamingException;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

import static org.springframework.ldap.query.LdapQueryBuilder.query;

@Service
public class UserRepositoryImpl implements UserRepository {

    private final UserDao userDao;
    private final RoleDao roleDao;
    private final LdapTemplate vnLdapTemplate;
    private final LdapTemplate jtLdapTemplate;
    private final LdapTemplate acLdapTemplate;

    public UserRepositoryImpl(UserDao userDao,
                              RoleDao roleDao,
                              @Qualifier("VN-LdapTemplate") LdapTemplate vnLdapTemplate,
                              @Qualifier("JT-LdapTemplate") LdapTemplate jtLdapTemplate,
                              @Qualifier("AC-LdapTemplate") LdapTemplate acLdapTemplate

    ) {
        this.userDao = userDao;
        this.roleDao = roleDao;
        this.vnLdapTemplate = vnLdapTemplate;
        this.jtLdapTemplate = jtLdapTemplate;
        this.acLdapTemplate = acLdapTemplate;
    }


    @Override
    public UserModel save(UserModel user) {
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
            Set<RoleModel> roleModels = new HashSet<>();
            roleDao.findRoleEntitiesByCode(RoleConstant.USER_ROLE).ifPresent(roleEntity -> {
                roleModels.add(new RoleModel(roleEntity.getCode(), roleEntity.getDescription()));
                roles.add(roleEntity);
            });
            user.setRoles(roleModels);
        }
        UserEntity userEntity = UserEntity.builder()
                .userName(user.getUserName())
                .displayName(user.getDisplayName())
                .password(user.getPassword())
                .roles(roles)
                .build();
        Optional<UserEntity> userDb = userDao.findUserEntitiesByUserName(user.getUserName());
        userDb.ifPresent(entity -> userEntity.setId(entity.getId()));
        UserEntity savedUser = userDao.save(userEntity);
        user.setUserId(savedUser.getId());
        return user;
    }

    @Override
    public void saveAll(List<UserModel> models) {
    }


    @Override
    public UserModel getById(Long id) {
        return null;
    }

    @Override
    public void update(UserModel user) {

    }

    @Override
    public UserModel getAccountInDbByUserName(String userName) {
        Optional<UserEntity> entity = userDao.findOneWithRolesByUserName(userName);
        return entity.map(UserMapper::toDomainModel)
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


    @Override
    @FrLoggable
    public UserModel getAccountFromADServerByUserName(String userName, CompanyEnum company) {
        LdapTemplate template = getLdapTemplate(company);
        if (ObjectUtils.isEmpty(template)) {
            return null;
        }
        List<UserModel> list = template.search(
                query().where("objectclass").is("person").and("sn").is(userName), new AttributesMapper<UserModel>() {
                    @Override
                    public UserModel mapFromAttributes(Attributes attrs) throws NamingException {
                        return UserModel.builder()
                                .displayName(attrs.get("cn").get().toString())
                                .userName(attrs.get("sn").get().toString())
                                .build();
                    }
                });
        if (list.size() == 1) {
            return list.get(0);
        }
        return null;
    }

    private LdapTemplate getLdapTemplate(CompanyEnum company) {
        switch (company) {
            case ATVN:
                return vnLdapTemplate;
            case JOY_TECH:
                return jtLdapTemplate;
            case ACCTON:
                return acLdapTemplate;
        }
        return null;
    }

    public boolean authenticateWithPassword(String userDn, String credentials, CompanyEnum company) {
        LdapTemplate template = getLdapTemplate(company);
        if (ObjectUtils.isEmpty(template)) {
            return false;
        }
        DirContext ctx = null;
        try {
            ctx = template.getContextSource().getContext(userDn, credentials);
            return true;
        } catch (Exception e) {
            // Context creation failed - authentication did not succeed
            return false;
        } finally {
            // It is imperative that the created DirContext instance is always closed
            LdapUtils.closeContext(ctx);
        }
    }
}
