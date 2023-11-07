package com.accton.newframework.core.domain.identity;

import com.accton.newframework.core.domain.IRepository;
import com.accton.newframework.core.domain.identity.model.RoleModel;
import com.accton.newframework.core.domain.identity.model.UserModel;

import java.util.Set;

public interface UserRepository extends IRepository<UserModel,Long> {

    void update(UserModel user);
    UserModel getByUserName(String userName);

    void addRoles(Set<RoleModel> roles);

}
