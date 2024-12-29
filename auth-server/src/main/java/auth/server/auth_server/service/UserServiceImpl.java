package auth.server.auth_server.service;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import auth.server.auth_server.model.User;
import auth.server.auth_server.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
    
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private PasswordEncoder passwordEncoder; 

    public Optional<User> findUserByName(String username){
        userRepository.findAll();
        return null;
    }

    public void SaveUser(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setStatus(1);
        user.setType(1);
        user.setUserName(user.getUserName());
        userRepository.save(user);

    }

      public String generateToken(String username) {
        return jwtService.generateToken(username);
    }

    public void validateToken(String token) {
        jwtService.validateToken(token);
    }


  
}
