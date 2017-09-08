package com.spring_boot2017127.demo.DaLRepository;

import com.spring_boot2017127.demo.ModelLayer.User;
import org.springframework.data.repository.CrudRepository;

public interface UzerRepository extends CrudRepository<User, Long> {

    User findByUsername(String username);
    User findByEmail(String email);
    Long countByEmail(String email);
    Long countByUsername(String username);
}
