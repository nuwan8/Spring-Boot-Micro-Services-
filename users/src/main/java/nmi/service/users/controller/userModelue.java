package nmi.service.users.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import nmi.service.users.controller.userModelue;
import nmi.service.users.service.ModuleServiceImpl;
import nmi.service.users.service.UserServiceImpl;


@RestController
@RequestMapping("/")
public class userModelue {

    @Autowired
    private UserServiceImpl serviceImpl;

    @Autowired
    private ModuleServiceImpl moduleServiceImpl;

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/user")
    public List getAllUserList(){
       return  serviceImpl.getAllUserList();
        // return "hello";
    }

    @CrossOrigin(origins = "http://localhost:3000")
     @GetMapping("/modules")
    public List getAllModulesList(){
       return  moduleServiceImpl.getAllModules();
        // return "hello";
    }


}
