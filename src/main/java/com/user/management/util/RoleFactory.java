package com.user.management.util;

import com.user.management.entity.Role;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

public class RoleFactory implements EntityFactory<Role>{
    private static final Logger logger = LoggerFactory.getLogger(RoleFactory.class);
    private final Scanner scanner = new Scanner(System.in);

    private final PermissionFactory permissionFactory;

    public RoleFactory(PermissionFactory permissionFactory) {
        this.permissionFactory = permissionFactory;
    }

    @Override
    public Role createNew() {
        logger.info("Creating new Role...");
        Role role = new Role();
        logger.info("""
                Enter Role Name:>""");
        role.setRoleName(scanner.next());
        logger.info("Role Created.");
        return role;
    }
}
