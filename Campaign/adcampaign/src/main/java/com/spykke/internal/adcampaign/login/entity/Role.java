package com.spykke.internal.adcampaign.login.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "login_role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToMany(mappedBy = "roles")
    private Set<LoginUser> users;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<LoginUser> getUsers() {
        return users;
    }

    public void setUsers(Set<LoginUser> users) {
        this.users = users;
    }
}
