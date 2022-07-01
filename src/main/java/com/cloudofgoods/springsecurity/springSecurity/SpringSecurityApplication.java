/**
 * @author - Chamath_Wijayarathna
 * Date :6/4/2022
 */

package com.cloudofgoods.springsecurity.springSecurity;

import com.cloudofgoods.springsecurity.springSecurity.model.Role;
import com.cloudofgoods.springsecurity.springSecurity.model.User;
import com.cloudofgoods.springsecurity.springSecurity.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

@SpringBootApplication
@EnableSwagger2
public class SpringSecurityApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringSecurityApplication.class, args);
    }

    @Bean
    /* Defined in the Spring Security configuration to encode the password.
       Here create a Spring bean and drop bean to spring context.
       Then WebSecurityConfig Class passwordEncoder get this bean object from
       Spring IOC and access*/
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    CommandLineRunner runner(UserService service ){
        return args -> {
            service.saveRole(new Role( null,"ROLE_USER"));
            service.saveRole(new Role( null,"ROLE_MANAGER"));
            service.saveRole(new Role( null,"ROLE_ADMIN"));
            service.saveRole(new Role( null,"ROLE_SUPER_ADMIN"));

            service.saveUser(new User(null,"John K", "john@gmail.com","1234",new ArrayList<>()));
            service.saveUser(new User(null,"Kamal", "Kamal","12345",new ArrayList<>()));
            service.saveUser(new User(null,"Nimal", "Nimal","1234",new ArrayList<>()));
            service.saveUser(new User(null,"AMri", "AMri","123",new ArrayList<>()));

            service.addRoleToUser("John","ROLE_USER");
            service.addRoleToUser("Kamal","ROLE_ADMIN");
            service.addRoleToUser("AMri","ROLE_ADMIN");
        };
    }
}
