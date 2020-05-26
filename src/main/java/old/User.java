package old;

public class User {


    public String password = "ysyghb";
    public String userName = "clarisite";

    public User() {
    }

    User(String userName, String password) {
        this.password = password;
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }
}
