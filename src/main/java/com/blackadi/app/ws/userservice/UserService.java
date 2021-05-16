package com.blackadi.app.ws.userservice;

import com.blackadi.app.ws.mobileappws.ui.model.request.UserDetailsRequestModel;
import com.blackadi.app.ws.mobileappws.ui.model.response.UserRest;

public interface UserService {
    UserRest createUser(UserDetailsRequestModel userDetails);
}
