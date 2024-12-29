package auth.server.auth_server.model;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;



@NoArgsConstructor
@AllArgsConstructor
@ToString
@Data
@Entity
@Table(name="user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     @Column(name="id")
     private int id;

    @Column(name="user_name")
    private String userName;

    @Column(name="password")
    private String password;

    @Column(name="status")
    private int status;

    @Column(name="type")
    private int type;


    @CreatedDate
    @Column(nullable = true, updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(nullable = true, updatable = false)
    private LocalDateTime updatedAt;
    
}
