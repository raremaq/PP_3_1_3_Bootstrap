package com.example.pp313.service;

import com.example.pp313.dao.UserDao;
import com.example.pp313.model.Role;
import com.example.pp313.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public List<User> allUsers() {
        return userDao.allUsers();
    }

    @Transactional
    @Override
    public void add(User user) {
        userDao.add(user);
    }

    @Transactional
    @Override
    public void remove(User user) {
        userDao.remove(user);
    }

    @Transactional
    @Override
    public void edit(User user) {
        userDao.edit(user);
    }

    @Override
    public User getById(long id) {
        return userDao.getById(id);
    }

    @Override
    public User findByUsername(String userName) {
        return userDao.findByUsername(userName);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.findByUsername(username);
        return Optional.ofNullable(user)
                .map(u -> new org.springframework.security.core.userdetails.User(u.getUsername(), u.getPassword(), mapRolesToAuthorities(u.getRoles())))
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream().
                map(r -> new SimpleGrantedAuthority(r.getName())).
                collect(Collectors.toList());
    }

    public User getCurrentUser() {
        UserDetails userDetails =
                (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return findByUsername(userDetails.getUsername());
    }
}
