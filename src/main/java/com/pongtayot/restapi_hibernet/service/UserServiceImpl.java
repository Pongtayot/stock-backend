package com.pongtayot.restapi_hibernet.service;

import com.pongtayot.restapi_hibernet.controllers.request.UserRequest;
import com.pongtayot.restapi_hibernet.exception.UserDuplicateException;
import com.pongtayot.restapi_hibernet.model.User;
import com.pongtayot.restapi_hibernet.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public User register(UserRequest userRequest) {
        User user = userRepository.findByUsername(userRequest.getUsername());
        if (user == null) {
            user = new User().setUsername(
                    userRequest.getUsername())
                    .setPassword(bCryptPasswordEncoder.encode(userRequest.getPassword()))
                    .setRole(userRequest.getRole());
            return userRepository.save(user);
        }
        throw new UserDuplicateException(userRequest.getUsername());
    }

    @Override
    public User findUserByUsername(String username) {
        Optional<User> user = Optional.ofNullable(userRepository.findByUsername(username));
        if (user.isPresent()){
            return user.get();
        }
        return null;
    }
}
