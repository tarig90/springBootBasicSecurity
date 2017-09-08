package com.spring_boot2017127.demo.ServiceRepository;


import com.spring_boot2017127.demo.DaLRepository.RoleRepository;
import com.spring_boot2017127.demo.DaLRepository.UzerRepository;
import com.spring_boot2017127.demo.ModelLayer.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class UserService {

    @Autowired
    UzerRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    public UserService(UzerRepository userRepository)
    {
        this.userRepository=userRepository;
    }

    public User findByEmail(String email)
    {
        return userRepository.findByEmail(email);
    }

    public Long countByEmail(String email)
    {
        return userRepository.countByEmail(email);
    }
    public User findByUsername(String username)
    {
        return userRepository.findByUsername(username);
    }


    public void saveUser(User user)
    {
        user.setRoles(Arrays.asList(roleRepository.findByRole("USER")));
        user.setEnabled(true);
        userRepository.save(user);
    }

    public void SaveAdmin(User user)
    {
        user.setRoles(Arrays.asList(roleRepository.findByRole("ADMIN")));
        user.setEnabled(true);
        userRepository.save(user);
    }
}
