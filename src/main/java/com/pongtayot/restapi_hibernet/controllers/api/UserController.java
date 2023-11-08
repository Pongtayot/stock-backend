package com.pongtayot.restapi_hibernet.controllers.api;

import com.pongtayot.restapi_hibernet.controllers.request.UserRequest;
import com.pongtayot.restapi_hibernet.exception.ValidationException;
import com.pongtayot.restapi_hibernet.model.User;
import com.pongtayot.restapi_hibernet.service.UserService;
import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/auth")
@RestController
public class UserController {
    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/register")
    public User register(@Valid @RequestBody UserRequest userRequest, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            bindingResult.getFieldErrors().stream().forEach(fieldError -> {
                throw new ValidationException(fieldError.getField() + ": " + fieldError.getDefaultMessage());
            });
        }
        return  userService.register(userRequest);
    }
}
