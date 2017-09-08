package com.spring_boot2017127.demo.DaLRepository;

import com.spring_boot2017127.demo.ModelLayer.Roles;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<Roles, Long> {

Roles findByRole(String role);

}
