package productmanager;

import java.util.ArrayList;
import java.util.List;

import productmanager.model.menu.AdminMenu;
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
            case 1: case 2:
                login();
                break;
            case 3:
                System.out.println("Thanks for using the product manager");
        
            default:
                break;
        }
        } while (option < 1 || option > productManagerMenu.loginMenuOptionSize());
    }

    private void userMenu(User user) {
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

    private void adminMenu(Admin admin) {
        AdminMenu adminMenu = AdminMenu.getInstance();
        int option;
        do {
            option = adminMenu.adminMenuOption(admin);
            switch (option) {
            case 1:
                viewProducts();
                break;
            case 2:
                System.out.println("Thanks for using the product manager");
        
            default:
                break;
        }
        } while (option < 1 || option > adminMenu.adminOptionsSize());

    }

    public void login() {
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
                    if (currentUser instanceof Admin) {
                        adminMenu((Admin) currentUser);
                    }
                    else {
                        userMenu(currentUser);
                    }
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

    private void viewProducts () {
        if (products.size() == 0) {
            System.out.println("No products available");
            return;
        }
        System.out.println("List of products: " + "\n");
        for (Product product : products) {
            System.out.println(product);
        }
        
    }

    private void addProduct (Admin admin) {
        Product product = productManagerMenu.inputProduct();
        products.add(product);
    } 

    private void removeProduct(Admin admin) {
        String name = productManagerMenu.inputProductName();
        if (products.removeIf(e -> e.getName().equals(name))) {
            System.out.println("Product removed successfully");
        }
        else {
            System.out.println("Product not found");
        }
    }

}
