package com.blackadi.app.ws.userservice.implementation;

import java.util.HashMap;
import java.util.Map;

import com.blackadi.app.ws.mobileappws.ui.model.request.UserDetailsRequestModel;
import com.blackadi.app.ws.mobileappws.ui.model.response.UserRest;
import com.blackadi.app.ws.shared.Utils;
import com.blackadi.app.ws.userservice.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * By declaring it as service, the framework will scan our packages and if it
 * sees the class which is annotated with Service, it will create an instance of
 * it and it will make it avaliable to to those classes that needed that request
 * to be autowired
 */
@Service
public class UserServiceImplementation implements UserService {

    Map<String, UserRest> users;
    Utils utils;

    public UserServiceImplementation() {
    }

    @Autowired
    public UserServiceImplementation(Utils utils) {
        this.utils = utils;
    }

    @Override
    public UserRest createUser(UserDetailsRequestModel userDetails) {

        UserRest returnVal = new UserRest();
        returnVal.setEmail(userDetails.getEmail());
        returnVal.setFristname(userDetails.getFirstName());
        returnVal.setLastName(userDetails.getLastName());

        String userId = utils.generateUserId();
        returnVal.setUserId(userId);

        if (users == null)
            users = new HashMap<>();
        users.put(userId, returnVal);

        return returnVal;
    }

}
