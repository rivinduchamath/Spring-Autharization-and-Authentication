package com.cloudofgoods.springsecurity.springSecurity.service.serviceImpl;

import com.cloudofgoods.springsecurity.springSecurity.model.Role;
import com.cloudofgoods.springsecurity.springSecurity.model.User;
import com.cloudofgoods.springsecurity.springSecurity.reppository.RoleRepository;
import com.cloudofgoods.springsecurity.springSecurity.reppository.UserRepository;
import com.cloudofgoods.springsecurity.springSecurity.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository repository;
    private final RoleRepository roleRepository;


    @Override
    public User saveUser(User user) {
        log.info("Inside the Save User " + user.getUserName());
        return repository.save(user);
    }

    @Override
    public Role saveRole(Role role) {
        log.info("Inside the SAve Role " + role.getName());
        return roleRepository.save(role);
    }

    // Add a Role Object To a User
    // One User May be having 1 or more Roles
    // UserName Must be Unique
    @Override
    public void addRoleToUser(String userName, String roleName) {
        User user = repository.findUserByUserName(userName);
        Role role = roleRepository.findRoleByName(roleName);
        log.info("Add Role " +roleName +" to the User " + userName);
        user.getRoles().add(role);
    }

    @Override
    public User getUser(String userNAme) {
        log.info("Get User " + userNAme);
        return repository.findUserByUserName(userNAme);
    }

    @Override
    // Cannot be applied to a real time application
    // Should be Limit
    public List<User> getUsers() {
        log.info("Get All Users");
        return repository.findAll();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = repository.findUserByUserName(username);
        if (user == null) {
            throw new UsernameNotFoundException("User Not Found on database");
        } else {

        }
        Collection authorities = new ArrayList<>();
        return new org.springframework.security.core.userdetails.User(user.getName(), user.getPassword(), authorities);
    }
}
