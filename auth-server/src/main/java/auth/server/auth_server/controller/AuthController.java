package auth.server.auth_server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import auth.server.auth_server.dto.AuthRequest;
import auth.server.auth_server.model.User;
import auth.server.auth_server.service.UserServiceImpl;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private UserServiceImpl service;

    @Autowired
    private AuthenticationManager authenticationManager;

    @GetMapping("/v")
    public String dataView(){
        return "ddddddd";
    }

    @PostMapping("/register")
    public String addNewUser(@RequestBody User user) {
     
        try{
              service.SaveUser(user);
              return "User Successfuly Added";
        }catch(Exception e){
            
              return "Opss Error occured";
             
        }
        
    }

  
    @PostMapping("/token")
    public String getToken(@RequestBody AuthRequest authRequest) {
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        if (authenticate.isAuthenticated()) {
            return service.generateToken(authRequest.getUsername());
        } else {
            throw new RuntimeException("invalid access");
        }

         
    }

    @GetMapping("/validate")
    public String validateToken(@RequestParam("token") String token) {
        service.validateToken(token);
        return "Token is valid";
    }
}
