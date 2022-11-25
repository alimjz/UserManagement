package com.user.management.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DisplayInfo {
    private static final Logger logger = LoggerFactory.getLogger(DisplayInfo.class);

    public static void displayActionBasedOnSelectedEntity(String operationEntity){
        switch (operationEntity){
            case "1" -> {
                logger.info("""
                        \n1. Create New User
                        2. Find By Id
                        3. List All User
                        4. Update User
                        5. Delete User""");
            }
            case "2" -> {
                logger.info("""
                        \n1. Create new Role
                        2. Find By Id
                        3. List All Role
                        4. Update Role
                        5. Delete Role""");
            }
            case "3" -> {
                logger.info("""
                        \n1. Create New Permission
                        2. Find By Id
                        3. List All Permissions
                        4. Update Permission
                        5. Delete Permission""");
            }
            default -> throw new RuntimeException("Incorrect Operation Entity");
        }
    }

}
