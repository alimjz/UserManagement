package com.user.management.util;

import com.user.management.entity.Permission;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PermissionFactory implements EntityFactory<List<Permission>> {
    private static final Logger logger = LoggerFactory.getLogger(PermissionFactory.class);
    private final Scanner scanner = new Scanner(System.in);
    private List<Permission> permissionArrayList = new ArrayList<>();

    @Override
    public List<Permission> createNew() {
        logger.info("Create new Permission...");
        Permission permission = new Permission();
        logger.info("""
                Enter Permission Name:>""");
        permission.setPermissionName(scanner.next());
        permissionArrayList.add(permission);
        logger.info("Permission defined.");
        return permissionArrayList;
    }
}
