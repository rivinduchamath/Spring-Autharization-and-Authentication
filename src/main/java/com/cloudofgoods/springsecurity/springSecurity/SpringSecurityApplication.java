package com.cloudofgoods.springsecurity.springSecurity;

import com.cloudofgoods.springsecurity.springSecurity.model.Role;
import com.cloudofgoods.springsecurity.springSecurity.model.User;
import com.cloudofgoods.springsecurity.springSecurity.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;

@SpringBootApplication
public class SpringSecurityApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringSecurityApplication.class, args);
    }


    @Bean
    CommandLineRunner runner(UserService service ){
        return args -> {
            service.saveRole(new Role( null,"ROLE_USER"));
            service.saveRole(new Role( null,"ROLE_MANAGER"));
            service.saveRole(new Role( null,"ROLE_ADMIN"));
            service.saveRole(new Role( null,"ROLE_SUPER_ADMIN"));

            service.saveUser(new User(null,"John K", "John","123456",new ArrayList<>()));
            service.saveUser(new User(null,"Kamal", "Kamal","12345",new ArrayList<>()));
            service.saveUser(new User(null,"Nimal", "Nimal","1234",new ArrayList<>()));
            service.saveUser(new User(null,"AMri", "AMri","123",new ArrayList<>()));

            service.addRoleToUser("John","ROLE_USER");
            service.addRoleToUser("Kamal","ROLE_ADMIN");
            service.addRoleToUser("AMri","ROLE_SUPER_ADMIN");
        };
    }
}
