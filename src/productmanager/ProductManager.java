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
       productManager.users.add(new Admin("alezxandro", "1234"));
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
                break;
        
            default:
                break;
        }
        } while (option >= 1 && option < productManagerMenu.loginMenuOptionSize());
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
                searchProduct(user);
                break;
            case 3:
                editProduct(user);
                break;
            default:
            System.out.println("Log out");
                break;
        }
        } while (option >= 1 && option < userMenu.userMenuOptionSize());


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
                searchProduct(admin);
            case 3:
                editProduct(admin);
                break;
            case 4:
                addProduct(admin);
                break;
            case 5:
                removeProduct(admin);
                break;
            default:
                System.out.println("Log out");
                break;
        }
        } while (option >= 1 && option < adminMenu.adminOptionsSize());

    }

    public void login() {
        String username = productManagerMenu.inputUsername();
        String password;
        
        User currentUser = null;

        for (User user : users) {
            if (user.getUsername().equals(username)) {
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
        if (product != null) {
            products.add(product);
            System.out.println("product added successfully");
        }
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

    private void editProduct (User user) {
        String name = productManagerMenu.inputProductName();
        Product product = null;

        for (Product p : products) {
            if (p.getName().equals(name)) {
                product = p;
                break;
            }
            
        }

        if (product != null) {
            int option = productManagerMenu.inputEditOption();

            switch (option) {
                case 1:
                    product.setName(productManagerMenu.inputProductName());
                    break;
                case 2:
                    product.setPrice(productManagerMenu.inputProductPrice());
                    break;
                case 3: 
                    product.setQuantity(productManagerMenu.inputProductQuantity());
                default:
                    break;
            }
        }
    }

    private void searchProduct (User user ) {

        System.out.println("Search product" + "\n");

        List<Product> foundProducts = new ArrayList<>();
        String name = productManagerMenu.inputProductName();
        for (Product product : products) {
            if (product.getName().equals(name)) {
                foundProducts.add(product);
            }
        }

        if (foundProducts.size() == 0) {
            System.out.println("\nno products found");
            return;
        }

        System.out.println("\nList of products found: ");

        for (Product product : foundProducts) {
            System.out.println(product);
        }

        System.out.println();
    }

}
