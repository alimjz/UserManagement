package com.user.management.repository;

import com.user.management.entity.User;

import java.util.List;

public interface UserRepository {
    User findById(Long id);
    List<User> findAll();
    void deleteById(Long id);
    User update(Long id);
}
