package productmanager.model.menu;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import productmanager.model.user.User;

public class UserMenu{
    private static Scanner scanner = new Scanner(System.in);

    private static UserMenu instance = null;
    private static List<String> userOptions;

    private UserMenu () {
        userOptions = new ArrayList<>();
        addUserOptions();
    }

    public void addUserOptions () {
        userOptions.add("1.View products");
        userOptions.add("2.Exit");
    }

    public int userMenuOption (User user) {
        System.out.println("Menu - " +  user.getUsername() + "\n");
        for (var option : userOptions) {
            System.out.println(option);
        }
        System.out.println();
        int option;
        do {
            System.out.print("Input option : ");
            option = scanner.nextInt();

        } while (option < 1 ||option > userOptions.size());        
        return option;
    }

    public static UserMenu getInstance() {
        if (instance == null) {
            instance = new UserMenu();
        }
        return instance;
    }
}
