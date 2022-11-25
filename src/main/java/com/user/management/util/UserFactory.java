package com.user.management.util;

import com.user.management.entity.Role;
import com.user.management.entity.User;
import com.user.management.service.RoleService;
import jakarta.persistence.EntityManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

public class UserFactory implements EntityFactory<User>{
    private static final Logger logger = LoggerFactory.getLogger(UserFactory.class);
    private final EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();

    private RoleFactory roleFactory;
    private final Scanner scanner = new Scanner(System.in);

    public UserFactory(RoleFactory roleFactory) {
        this.roleFactory = roleFactory;
    }

    public UserFactory(){}

    @Override
    public User createNew(){
        logger.info("Creating new User...");
        User user = new User();
        logger.info("""
                \nEnter user name:>""");
        user.setUserName(scanner.nextLine());
        logger.info("""
                \nEnter user National Id:>""");
        user.setUserNationalId(scanner.nextLine());
        return user;
    }

    public Role setUserRoleById(Long id){
        logger.info("Enter Existing Role Id:>");
        return new RoleService(entityManager).findById(id);
    }
}
