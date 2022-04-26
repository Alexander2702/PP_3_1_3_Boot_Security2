package ru.kata.spring.boot_security.demo.models;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Entity
@Table(name = "roles")
public class Role implements GrantedAuthority {
    @Id
    @Column(name = "id")
    private int id;
    @Column(name = "roles_user")
    private String rolesUser;

    public Role() {
    }

    public Role(int id) {
        this.id = id;
    }

    public Role(int id, String rolesUser) {
        this.id = id;
        this.rolesUser = rolesUser;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRolesUser() {
        return rolesUser;
    }

    public void setRolesUser(String rolesUser) {
        this.rolesUser = rolesUser;
    }

    @Override
    public String getAuthority() {
        return getRolesUser();
    }

    @Override
    public String toString() {
        return  rolesUser;

//                "Role{" +
//                "id=" + id +
//                ", rolesUser='" + rolesUser + '\'' +
//                '}';
    }
}
