package com.user.management.service;

import java.util.List;

public interface Service<T> {
    void save();
    T findById(Long id);


    List<T> findAll();


    T update(Long id);

    void delete(Long id);
}
