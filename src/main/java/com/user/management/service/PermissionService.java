package com.user.management.service;

import com.user.management.entity.Permission;
import com.user.management.util.PermissionFactory;
import jakarta.persistence.EntityManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Scanner;

public class PermissionService implements Service<Permission> {
    private static final Logger logger = LoggerFactory.getLogger(PermissionService.class);
    private final EntityManager entityManager;
    PermissionFactory permissionFactory = new PermissionFactory();
    private Scanner scanner = new Scanner(System.in);

    public PermissionService(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void save() {
        entityManager.getTransaction().begin();
        List<Permission> permissionList = permissionFactory.createNew();
        permissionList.forEach(entityManager::persist);
        entityManager.getTransaction().commit();
    }

    @Override
    public List<Permission> findAll() {
        return entityManager.createQuery("select p from Permission p", Permission.class).getResultList();
    }

    @Override
    public Permission findById(Long id) {
        Permission permission = entityManager.find(Permission.class, id);
        logger.info(permission.toString());
        return permission;
    }

    @Override
    public Permission update(Long id) {
        entityManager.getTransaction().begin();
        Permission permission = findById(id);
        logger.info("\nEnter Permission Name:>");
        permission.setPermissionName(scanner.nextLine());
        entityManager.persist(permission);
        entityManager.getTransaction().commit();
        return permission;
    }

    @Override
    public void delete(Long id) {
        entityManager.getTransaction().begin();
        entityManager.remove(findById(id));
        entityManager.getTransaction().commit();
    }
}
