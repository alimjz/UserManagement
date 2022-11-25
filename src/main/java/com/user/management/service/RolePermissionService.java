package com.user.management.service;

import com.user.management.entity.RolePermission;
import jakarta.persistence.EntityManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Scanner;

public class RolePermissionService implements Service<RolePermission> {
    private static final Logger logger = LoggerFactory.getLogger(RoleService.class);
    private EntityManager entityManager;
    Scanner scanner = new Scanner(System.in);
    PermissionService permissionService = new PermissionService(entityManager);
    RoleService roleService = new RoleService(entityManager);

    public RolePermissionService(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void save() {
        RolePermission rolePermission = new RolePermission();
        logger.info("\nEnter Role Id:>");
        rolePermission.setRole(roleService.findById(scanner.nextLong()));
        logger.info("\nEnter Permission Id:>");
        rolePermission.setPermission(permissionService.findById(scanner.nextLong()));
        entityManager.getTransaction().begin();
        entityManager.persist(rolePermission);
        entityManager.getTransaction().commit();
        logger.info("Permission added.");
    }

    @Override
    public RolePermission findById(Long id) {
        return entityManager.find(RolePermission.class,id);
    }

    @Override
    public List<RolePermission> findAll() {
        entityManager.getTransaction().begin();
        List<RolePermission> rolesPermissions = entityManager.createQuery("select rp from RolePermission rp",RolePermission.class).getResultList();
        entityManager.getTransaction().commit();
        return rolesPermissions;
    }

    @Override
    public RolePermission update(Long id) {
        return null;
    }

    @Override
    public void delete(Long id) {
        entityManager.getTransaction().begin();
        entityManager.remove(findById(id));
        entityManager.getTransaction().commit();
    }

    public List<RolePermission> findRolesWithPermissionId(Long id) {
        List<RolePermission> rolePermissions = entityManager.createQuery("select rp from RolePermission rp where rp.permission = :id", RolePermission.class)
                .setParameter("id", id).getResultList();
        return rolePermissions;
    }

    public List<RolePermission> findPermissionsWithRoleId(Long id){
        List<RolePermission> rolePermissions = entityManager.createQuery("select rp from RolePermission rp where rp.role = :id", RolePermission.class)
                .setParameter("id",id).getResultList();
        return rolePermissions;
    }
}
