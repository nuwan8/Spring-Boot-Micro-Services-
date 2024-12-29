package auth.server.auth_server.service;

import java.util.Optional;

import auth.server.auth_server.model.User;


public interface UserService {

    Optional<User> findUserByName(String username); 
    
}
