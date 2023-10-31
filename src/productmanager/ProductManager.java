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
                    UserMenu userMenu = UserMenu.getInstance();
                    userMenu.userMenuOption(currentUser);
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

}
