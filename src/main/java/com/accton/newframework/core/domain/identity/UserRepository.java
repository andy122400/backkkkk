package com.accton.newframework.core.domain.identity;

import com.accton.newframework.core.domain.IRepository;
import com.accton.newframework.core.domain.identity.model.RoleModel;
import com.accton.newframework.core.domain.identity.model.UserModel;
import com.accton.newframework.utility.contants.CompanyEnum;

import java.util.Set;

public interface UserRepository extends IRepository<UserModel,Long> {

    void update(UserModel user);
    UserModel getAccountInDbByUserName(String userName);

    void addRoles(Set<RoleModel> roles);

    UserModel getAccountFromADServerByUserName(String userName, CompanyEnum company);
    boolean authenticateWithPassword(String userDn, String credentials, CompanyEnum company);

}
