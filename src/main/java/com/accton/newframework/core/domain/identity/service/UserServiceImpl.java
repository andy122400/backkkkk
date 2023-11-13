package com.accton.newframework.core.domain.identity.service;

import com.accton.newframework.core.application.logging.FrLoggable;
import com.accton.newframework.core.domain.identity.UserRepository;
import com.accton.newframework.core.domain.identity.model.RoleModel;
import com.accton.newframework.core.domain.identity.model.UserModel;
import com.accton.newframework.utility.contants.CompanyEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.Set;


@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final EncryptService encryptService;

    @Autowired
    public UserServiceImpl(UserRepository userRepository,
                           EncryptService encryptService) {
        this.userRepository = userRepository;
        this.encryptService = encryptService;
    }

    @FrLoggable
    @Override
    public UserModel findUser(String userName, String password, CompanyEnum company) {
        UserModel userModel = userRepository.getAccountInDbByUserName(userName);
        if (userModel != null ) {
            if (!ObjectUtils.isEmpty(userModel.getPassword()) && encryptService.matchPassword(password, userModel.getPassword())){
                return userModel;
            }
        }
        if (userModel == null || ObjectUtils.isEmpty(userModel.getPassword())){
            userModel= userRepository.getAccountFromADServerByUserName(userName,company );
            boolean isValid = userRepository.authenticateWithPassword(userModel.getDisplayName(), password,company);
            if (isValid){
                return  userRepository.save(userModel);
            }
        }
        return null;
    }

    @Override
    public UserModel addUser(UserModel model) {
        if (!ObjectUtils.isEmpty(model.getPassword())) {
            model.setPassword(encryptService.hashPassword(model.getPassword()));
        }
        return userRepository.save(model);
    }

    @Override
    public void addRoles(Set<RoleModel> roles) {
        userRepository.addRoles(roles);
    }

}
