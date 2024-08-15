package com.example.pp313.service;

import com.example.pp313.dao.RoleDao;
import com.example.pp313.model.Role;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@AllArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleDao roleDao;

    @Override
    public Collection<Role> allRoles() {
        return roleDao.findAll();
    }
}
