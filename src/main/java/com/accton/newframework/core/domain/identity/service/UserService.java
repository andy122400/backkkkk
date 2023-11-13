package com.accton.newframework.core.domain.identity.service;

import com.accton.newframework.core.domain.identity.model.RoleModel;
import com.accton.newframework.core.domain.identity.model.UserModel;
import com.accton.newframework.utility.contants.CompanyEnum;

import java.util.Set;


public interface UserService {

    UserModel findUser(String userName, String password, CompanyEnum company);
    UserModel addUser(UserModel model);
    void addRoles(Set<RoleModel> roles);


}
