package ru.kata.spring.boot_security.demo.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.repository.RoleRepository;

import javax.transaction.Transactional;
import java.util.List;
@Repository
public class RoleDaoImpl implements RoleDao {
    @Autowired
    RoleRepository roleRepository;

    @Transactional
    @Override
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }
    @Transactional
    @Override
    public Role getRoleByRolesUser(String name) {
        return roleRepository.getRoleByRolesUser(name);
    }
}
