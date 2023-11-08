package com.pongtayot.restapi_hibernet.service;

import com.pongtayot.restapi_hibernet.controllers.request.UserRequest;
import com.pongtayot.restapi_hibernet.model.User;

public interface UserService {
    User register(UserRequest userRequest);

    User findUserByUsername(String username);
}
