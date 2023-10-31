package productmanager.model.user;

public class User {
    private int id;
    private String username;
    private String password;
    private String name;
    private String surname;


    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    private String getPassword() {
        return password;
    }
    public String getName() {
        return name;
    }
    public String getSurname() {
        return surname;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setSurname(String surname) {
        this.surname = surname;
    }

    public boolean checkPassword(String password) {
        return password.equals(getPassword());
    } 

    public boolean isAdmin () {
        return this instanceof Admin;
    }

    
}
