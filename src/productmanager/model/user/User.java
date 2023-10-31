package productmanager.model.user;

public class User {
    private static int idCounter = 1;
    private int id;
    private String username;
    private String password;

    public User(String username, String password) {
        this.id = idCounter++;
        this.username = username;
        this.password = password;
    }

    public boolean checkPassword (String inputPassword) {
        return inputPassword.equals(password);
    }

    public int getId() {
        return id;
    }

    private void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    private String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    

    

    


}
