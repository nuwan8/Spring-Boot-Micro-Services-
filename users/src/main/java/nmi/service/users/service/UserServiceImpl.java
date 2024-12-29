package nmi.service.users.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nmi.service.users.model.User;
import nmi.service.users.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUserList(){
        
        List<User> list = userRepository.findAll();
        return list;
    }
    
}
