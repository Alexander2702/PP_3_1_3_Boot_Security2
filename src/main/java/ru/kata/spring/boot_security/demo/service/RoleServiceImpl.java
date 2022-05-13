package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.dao.RoleDao;
import ru.kata.spring.boot_security.demo.models.Role;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService{
    @Autowired
    RoleDao roleDao;

    @Override
    public List<Role> getAllRoles() {
        return roleDao.getAllRoles();
    }

    @Override
    public Role getRoleByRolesUser(String name) {
        return null;
    }
}
