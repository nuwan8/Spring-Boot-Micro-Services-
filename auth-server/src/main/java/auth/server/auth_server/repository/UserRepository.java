package auth.server.auth_server.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import auth.server.auth_server.model.User;

public interface UserRepository extends JpaRepository<User,Integer> {

    
} 