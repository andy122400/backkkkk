package com.accton.newframework.core.domain.identity;

import com.accton.newframework.core.domain.identity.model.RoleModel;
import com.accton.newframework.core.domain.identity.model.UserModel;

import java.util.Set;


public interface UserService {

    UserModel findUserByUserNamePassword(String userName,String password, boolean isAdUser);
    UserModel addUser(UserModel model);
    void addRoles(Set<RoleModel> roles);


}
