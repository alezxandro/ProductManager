package productmanager;

import java.util.ArrayList;
import java.util.List;

import productmanager.model.menu.ProductManagerMenu;
import productmanager.model.menu.UserMenu;
import productmanager.model.product.Product;
import productmanager.model.user.Admin;
import productmanager.model.user.User;

public class ProductManager {

    private List<User> users;
    private List<Product> products;
    private ProductManagerMenu productManagerMenu;

    public ProductManager() {
        users = new ArrayList<>();
        products = new ArrayList<>();
        productManagerMenu = ProductManagerMenu.getInstance();
    }

    public static void main(String[] args) {
       ProductManager productManager = new ProductManager();
       productManager.users.add(new User("alezxandro", "1234"));
       productManager.menu();
    }

    public void menu() {
        int option;
        do {
             option = productManagerMenu.loginMenuOption();
            switch (option) {
            case 1:
                loginUser();
                break;
            case 2:
                System.out.println("Thanks for using the product manager");
        
            default:
                break;
        }
        } while (option < 1 || option > productManagerMenu.loginMenuOptionSize());
    }

    public void userMenu(User user) {
        UserMenu userMenu = UserMenu.getInstance();

        int option;
        do {
            option = userMenu.userMenuOption(user);
            switch (option) {
            case 1:
                viewProducts();
                break;
            case 2:
                System.out.println("Thanks for using the product manager");
        
            default:
                break;
        }
        } while (option < 1 || option > userMenu.userMenuOptionSize());


    }

    public void loginUser() {
        String username = productManagerMenu.inputUsername();
        String password;
        
        User currentUser = null;

        for (User user : users) {
            if (user.getUsername().equals(username) && !(user instanceof Admin)) {
                currentUser =  user;
                break;
            }
        }

        if (currentUser != null) {
            System.out.println("Found");
            int attempts = 3; 
            do {
                password = productManagerMenu.inputPassword();
                if (currentUser.checkPassword(password)) {
                    userMenu(currentUser);
                    break;
                }
                attempts--;
            } while (attempts >= 0);
            return;
        }
        else {
            System.out.println("User not found");
        }
    }

    public void viewProducts () {
        if (products.size() == 0) {
            System.out.println("No products available");
            return;
        }
        System.out.println("List of products: " + "\n");
        for (Product product : products) {
            System.out.println(product);
        }
    }

}
