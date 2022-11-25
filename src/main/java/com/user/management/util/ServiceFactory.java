package com.user.management.util;

import com.user.management.service.PermissionService;
import com.user.management.service.RoleService;
import com.user.management.service.Service;
import com.user.management.service.UserService;
import jakarta.persistence.EntityManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ServiceFactory {
    static EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();

    private static final Logger logger = LoggerFactory.getLogger(ServiceFactory.class);

    public static Service<?> createService(String operationEntity) {
        switch (operationEntity) {
            case "1" -> {
                logger.info("User Service Initiated.");
                return new UserService(entityManager);
            }
            case "2" -> {
                logger.info("Role Service Initiated.");
                return new RoleService(entityManager);
            }
            case "3" -> {
                logger.info("Permission Service Initiated.");
                return new PermissionService(entityManager);
            }
            default -> throw new RuntimeException("Incorrect Operation Entity");
        }
    }
}
