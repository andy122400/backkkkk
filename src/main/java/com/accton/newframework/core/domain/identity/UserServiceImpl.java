package com.accton.newframework.core.domain.identity;

import com.accton.newframework.core.domain.identity.model.RoleModel;
import com.accton.newframework.core.domain.identity.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;


@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public UserModel findOneByUserName(String userName) {
        return userRepository.getByUserName(userName);
    }

    @Override
    public UserModel addUser(UserModel model) {
        return userRepository.add(model);
    }

    @Override
    public void addRoles(Set<RoleModel> roles) {
        userRepository.addRoles(roles);
    }

}
