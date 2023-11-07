package com.accton.newframework.core.domain.identity;

import com.accton.newframework.core.domain.identity.model.RoleModel;
import com.accton.newframework.core.domain.identity.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.Set;


@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository,
                           PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserModel findOneByUserName(String userName) {
        return userRepository.getByUserName(userName);
    }

    @Override
    public UserModel addUser(UserModel model) {
        if (!ObjectUtils.isEmpty(model.getPassword())){
            model.setPassword(passwordEncoder.encode(model.getPassword()));
        }
        return userRepository.add(model);
    }

    @Override
    public void addRoles(Set<RoleModel> roles) {
        userRepository.addRoles(roles);
    }

}
