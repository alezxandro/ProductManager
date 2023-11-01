package productmanager.model.menu;

import java.util.Scanner;

public class Menu {
    protected Scanner scanner = new Scanner(System.in);

    public void clearBuffer () {
        if (scanner.hasNextLine()) {
            scanner.nextLine();
        }
    }
}
