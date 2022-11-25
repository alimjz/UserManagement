package com.user.management.service;

import com.user.management.entity.Role;
import com.user.management.entity.RolePermission;
import com.user.management.util.JPAUtil;
import com.user.management.util.PermissionFactory;
import com.user.management.util.RoleFactory;
import jakarta.persistence.EntityManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Scanner;

public class RoleService implements Service<Role> {
    private static final Logger logger = LoggerFactory.getLogger(RoleService.class);
    private EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
    RoleFactory roleFactory = new RoleFactory(new PermissionFactory());
    Scanner scanner = new Scanner(System.in);
    PermissionService permissionService = new PermissionService(entityManager);
    public RoleService(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void save() {
        entityManager.getTransaction().begin();
        entityManager.persist(roleFactory.createNew());
        entityManager.getTransaction().commit();
    }

    @Override
    public List<Role> findAll(){
        entityManager.getTransaction().begin();
        List<Role> roles = entityManager.createQuery("select r from Role r",Role.class).getResultList();
        entityManager.getTransaction().commit();
        return roles;
    }

    @Override
    public Role findById(Long id) {
        Role role = entityManager.find(Role.class,id);
        logger.info("\n"+role.toString());
        return role;
    }

    @Override
    public Role update(Long id) {
        entityManager.getTransaction().begin();
        Role role = findById(id);
        logger.info("\nEnter Role Name:>");
        role.setRoleName(scanner.nextLine());
        RolePermission rolePermission = new RolePermission();
        logger.info("\nChoose Permission Id below:>");
        permissionService.findAll().forEach(System.out::println);
        rolePermission.setPermission(permissionService.findById(scanner.nextLong()));
        rolePermission.setRole(role);
        entityManager.persist(rolePermission);
        entityManager.persist(role);
        entityManager.getTransaction().commit();
        return role;
    }

    @Override
    public void delete(Long id) {
        entityManager.getTransaction().begin();
        entityManager.remove(findById(id));
        entityManager.getTransaction().commit();
    }
}
