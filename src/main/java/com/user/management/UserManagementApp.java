package com.user.management;

import com.user.management.service.Service;
import com.user.management.util.DisplayInfo;
import com.user.management.util.ServiceFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;
public class UserManagementApp {

    private static final Logger logger = LoggerFactory.getLogger(UserManagementApp.class);
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean liveness = true;
        logger.info("Welcome to Digipay User Management System");
        while (liveness){
            logger.info( "\nSelect Main Sector:" +
                    "\n1. User Operations" + "\n2. Role Operations" + "\n3. Permission Operations");
            String operationEntity = scanner.nextLine();
            DisplayInfo.displayActionBasedOnSelectedEntity(operationEntity);
            String command = scanner.nextLine();
            Service<?> service= ServiceFactory.createService(operationEntity);
            execute(command,service);
            logger.info("\nDo u need any other operation?");
            if (scanner.nextLine().equalsIgnoreCase("n")){
                liveness = false;
                logger.info("\nExiting...");
            }
        }
    }

    private static void execute(String command, Service<?> service) {
        switch (command){
            case "1":
                service.save();
                break;
            case "2":
                logger.info("Enter Id:>");
                service.findById(Long.valueOf(scanner.nextLine()));
                break;
            case "3":
                service.findAll().forEach(System.out::println);
                break;
            case "4":
                logger.info("Enter Id:>");
                service.update(Long.valueOf(scanner.nextLine()));
                break;
            case "5":
                logger.info("Enter Id:>");
                service.delete(Long.valueOf(scanner.nextLine()));
                break;
            default:
                throw new RuntimeException("IllegalArgument...");
        }
    }
}
