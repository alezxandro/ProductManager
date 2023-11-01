package productmanager.model.menu;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import productmanager.model.product.Product;

public class ProductManagerMenu {
    private static Scanner scanner = new Scanner(System.in);
    private static ProductManagerMenu instance = null;
    private static List<String> loginOptions;
    private static List<String> editOptions;

    private ProductManagerMenu() {
        loginOptions = new ArrayList<>();
        addLoginOptions();
        addEditProductOptions();
    }

    private void addLoginOptions() {
        loginOptions.add("1.Login as user");
        loginOptions.add("2.Login as admin");
        loginOptions.add("3.Exit");
    }

    public int loginMenuOption () {
        System.out.println("Menu Product Manager" + "\n");
        for (var option : loginOptions) {
            System.out.println(option);
        }
        System.out.println();
        int scelta;
        do {
            System.out.print("Input option : ");
            scelta = scanner.nextInt();

        } while (scelta < 1 ||scelta > loginOptions.size());        
        return scelta;
    }

    public String inputUsername () {
        scanner.nextLine();
        System.out.print("Input username: ");
        String username = scanner.nextLine();
        return username;

    }

    public String inputPassword () {
        System.out.print("Input password: ");
        String password = new String(System.console().readPassword());
        return password;
    }

    public static ProductManagerMenu getInstance() {
        if(instance == null) {
            instance = new ProductManagerMenu();
        }
        return instance;
    }

    public int loginMenuOptionSize () {
        return loginOptions.size();
    }

    public Product inputProduct () {

        String name = inputProductName();
        double price = inputProductPrice();
        int quantity = inputProductQuantity();
        
        return new Product(name, price, quantity);

    }

    public String inputProductName() {
        String name;
        do {
            System.out.print("Insert the name of the product: ");
            name = scanner.nextLine();
        } while (name.isBlank());
        return name;
    }
    public double inputProductPrice() {
        double price;
        do {
            System.out.print("Insert the price of the product: ");
             price = scanner.nextDouble();
        } while (price < 0.0);
        return price;
    }
    public int inputProductQuantity() {
        int quantity;
        do {
            System.out.print("Insert the quantity of the product: ");
            quantity = scanner.nextInt();
        } while (quantity < 0);
        return quantity;
    }

    public void addEditProductOptions () {
        editOptions.add("1.Edit name");
        editOptions.add("2.Edit price");
        editOptions.add("3.Edit quantity");
    }

    public int inputEditOption () {
        int option;
        System.out.println("Edit product menu");
        for (String e : editOptions) {
            System.out.println(e);
        }
        do {
            System.out.print("Input option: ");
            option = scanner.nextInt();
        } while(option < 1 || option > editOptions.size());

        return option;

    }    

}
