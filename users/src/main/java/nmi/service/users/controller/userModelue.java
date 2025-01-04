package nmi.service.users.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import nmi.service.users.controller.userModelue;
import nmi.service.users.service.ModuleServiceImpl;
import nmi.service.users.service.UserServiceImpl;


@RestController
@RequestMapping("/user")
public class userModelue {

    @Autowired
    private UserServiceImpl serviceImpl;

    @Autowired
    private ModuleServiceImpl moduleServiceImpl;

   
    
      @GetMapping("/list")
    public List getAllUserList(){
       return  serviceImpl.getAllUserList();
        // return "hello";
    }



     @GetMapping("/modules")
    public List getAllModulesList(){
       return  moduleServiceImpl.getAllModules();
        // return "hello";
    }


}
