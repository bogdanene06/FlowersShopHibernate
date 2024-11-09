/*
Author: Ene Bogdan
Country: Romania
*/
package utils;

import com.github.javafaker.Faker;
import entity.Product;
import entity.daoImpl.ClientDAOImpl;
import entity.daoImpl.FlowerNameGenerator;
import entity.daoImpl.OrdersDAOImpl;
import entity.daoImpl.ProductDAOImpl;
import lombok.extern.java.Log;

import java.util.Random;
import java.util.Scanner;

@Log
public class MainMenu {
    static Scanner scanner = new Scanner(System.in);
    static Random random = new Random();
    static Faker faker = new Faker();

    static ProductDAOImpl productDAO = new ProductDAOImpl();
    static OrdersDAOImpl ordersDAO = new OrdersDAOImpl();
    static ClientDAOImpl clientDAO = new ClientDAOImpl();

    static ProductsOperations productsOperations = new ProductsOperations();
    static OrdersOperations ordersOperations = new OrdersOperations(scanner, ordersDAO, clientDAO);
    static ClientsOperations clientsOperations = new ClientsOperations(scanner, clientDAO);

    public void mainMenuOptions() {
        int mainMenuOption;
        boolean exitApplication = false;

        do {
            System.out.println("""
                    Choose the desired option:
                    1. Clients options
                    2. Orders options
                    3. Products options
                    4. Exit the application!
                    
                    Type it here: """);

            if (scanner.hasNextInt()) {
                mainMenuOption = scanner.nextInt();

                switch (mainMenuOption) {
                    case 1:
                        clientsOperations.clientsMenu();
                        break;

                    case 2:
                        ordersOperations.ordersMenu();
                        break;

                    case 3:
                        productsOperations.productsMenu();
                        break;

                    case 4:
                        exitApplication = exitingApplication(scanner);
                        break;

                    default:
                        log.warning("Invalid OPTION on main menu. Choose an option between 1 to 4!");
                }
            } else {
                scanner.next();
                log.warning("Invalid CHARACTER TYPE on choosing main menu options. Type a NUMBER between 1 to 4!");
            }
        } while (!exitApplication);
    }

    private boolean exitingApplication(Scanner scanner) {
        System.out.println("Are you sure you want to exit? (YES/NO)");
        String confirmation = scanner.next();
        if (confirmation.equalsIgnoreCase("yes") || confirmation.equalsIgnoreCase("y")) {
            System.out.println("Exiting the main application... Goodbye!");
            scanner.close();
            System.exit(0);
            return true;
        } else if (confirmation.equalsIgnoreCase("no") || confirmation.equalsIgnoreCase("n")) {
            System.out.println("Returning to application...");
            return false;
        } else {
            System.out.println("Invalid input. Please type either 'y'/'n' or 'yes'/'no' (without quotes; insensitive " +
                               "case).");
            return exitingApplication(scanner);
        }
    }

    public void createRandomProducts() {
        int randomNumberOfRandomProducts = faker.number().numberBetween(1, 30);
        for (int i = 0; i < randomNumberOfRandomProducts; i++) {
            Product product = new Product();
            product.setName(FlowerNameGenerator.generateRandomFlowerName());
            product.setPrice(random.nextFloat(10, 1000));
            productDAO.createProduct(product);
        }
        if (randomNumberOfRandomProducts == 1) {
            log.info("The product has been successfully created.");
        } else if (randomNumberOfRandomProducts > 1) {
            log.info("The products have been successfully created.");
        } else {
            log.warning("Number of products cannot be NEGATIVE. Please insert a number above 0 (zero)!");
        }
        System.out.println();
    }

    public void createRandomClients(){
        int numberOfRandomClients = faker.number().numberBetween(1, 10);
        for (int i = 0; i < numberOfRandomClients; i++) {
            clientDAO.createClient();
        }
        if (numberOfRandomClients == 1) {
            log.info("The client has been successfully created.");
        } else if (numberOfRandomClients > 1) {
            log.info("The clients have been successfully created.");
        } else {
            log.warning("Number of clients cannot be NEGATIVE. Please insert a number above 0 (zero)!");
        }
        System.out.println();
    }

}
