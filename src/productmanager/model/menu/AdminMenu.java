package productmanager.model.menu;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import productmanager.model.user.User;
import productmanager.model.user.Admin;

public class AdminMenu extends Menu{
    private static List<String> adminOptions;
    private static AdminMenu instance = null;

    private AdminMenu() {
        adminOptions = new ArrayList<>();
        addAdminOptions();
    }

    public void addAdminOptions () {
        adminOptions.add("1.View products");
        adminOptions.add("2.Search products");
        adminOptions.add("3.Update products");
        adminOptions.add("4.Create product");
        adminOptions.add("5.Delete products");
        adminOptions.add("6.Exit");
    }

    public int adminMenuOption (Admin admin) {
        System.out.println("Menu - " +  admin.getUsername() + "\n");
        for (var option : adminOptions) {
            System.out.println(option);
        }
        System.out.println();
        int option;
        do {
            System.out.print("Input option : ");
            option = scanner.nextInt();
            System.out.println();

        } while (option < 1 ||option > adminOptions.size());        
        return option;
    }
    public static AdminMenu getInstance() {
        if (instance == null) {
            instance = new AdminMenu();
        }
        return instance;
    }
    public int adminOptionsSize() {
        return adminOptions.size();
    }

}
