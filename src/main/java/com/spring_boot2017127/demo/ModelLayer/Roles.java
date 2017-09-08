package com.spring_boot2017127.demo.ModelLayer;


import javax.persistence.*;
import java.util.Collection;

@Entity
public class Roles {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "role")
    private String role;

    @ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
    private Collection<User> users;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Collection<User> getUsers() {
        return users;
    }

    public void setUsers(Collection<User> users) {
        this.users = users;
    }
}
