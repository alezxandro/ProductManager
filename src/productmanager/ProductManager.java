package productmanager;

import java.util.ArrayList;
import java.util.List;

import productmanager.model.menu.ProductManagerMenu;
import productmanager.model.user.Admin;
import productmanager.model.user.User;

public class ProductManager {

    private List<User> users;
    private ProductManagerMenu productManagerMenu;

    public ProductManager() {
        users = new ArrayList<>();
        productManagerMenu = ProductManagerMenu.getInstance();
    }

    public static void main(String[] args) {
       ProductManager productManager = new ProductManager();
       productManager.users.add(new User("alezxandro", "1234"));
       productManager.menu();
    }

    public void menu() {
        int scelta;
        do {
             scelta = productManagerMenu.loginMenuOption();
            switch (scelta) {
            case 1:
                loginUser();
                break;
            case 2:
                System.out.println("Thanks for using the product manager");
        
            default:
                break;
        }
        } while (scelta < 1 || scelta > productManagerMenu.loginMenuOptionSize());
    }

    public void loginUser() {
        String username = productManagerMenu.inputUsername();
        String password = productManagerMenu.inputPassword();

        User currentUser = null;

        for (User user : users) {
            if (user.getUsername().equals(username) && !(user instanceof Admin) && user.checkPassword(password)) {
                currentUser =  user;
                break;
            }
        }

        if (currentUser != null) {
            System.out.println("Found");
        }
        else {
            System.out.println("Users not found or incorrect password");
        }
    }

}
