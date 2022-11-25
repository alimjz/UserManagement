package com.user.management.service;

import com.user.management.entity.User;
import com.user.management.util.PermissionFactory;
import com.user.management.util.RoleFactory;
import com.user.management.util.UserFactory;
import jakarta.persistence.EntityManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Scanner;

public class UserService implements Service<User>{
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);
    private final EntityManager entityManager;
    UserFactory userFactory = new UserFactory(new RoleFactory(new PermissionFactory()));

    Scanner scanner = new Scanner(System.in);

    public UserService(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    @Override
    public void save(){
        entityManager.getTransaction().begin();
        User user = userFactory.createNew();
//        entityManager.persist(user.getRole());
        entityManager.persist(user);
        entityManager.getTransaction().commit();
    }

    @Override
    public User findById(Long id){
        User user =  entityManager.find(User.class,id);
        logger.info("\n"+user.toString());
        return user;
    }

    @Override
    public List<User> findAll(){
        return  entityManager.createQuery("select u from User u",User.class).getResultList();
    }

    @Override
    public User update(Long id){
        entityManager.getTransaction().begin();
        User user = findById(id);
        logger.info("\nEnter Username:>");
        user.setUserName(scanner.nextLine());
        logger.info("\nEnter NationalId:>");
        user.setUserNationalId(scanner.nextLine());
        logger.info("\nEnter Role Id:>");
        user.setRole(new UserFactory().setUserRoleById(Long.valueOf(scanner.nextLine())));
        entityManager.persist(user);
        entityManager.getTransaction().commit();
        return user;
    }

    @Override
    public void delete(Long id) {
        entityManager.getTransaction().begin();
        entityManager.remove(findById(id));
        entityManager.getTransaction().commit();
    }
}
