public abstract class User {
    private String name;
    private String userID;
    private String password;

    public User(String name, String userID, String password) {
        this.name = name;
        this.userID = userID;
        this.password = password;
    }
    public User(){}

    public String getName() {
        return name;
    }

    public String getUserID() {
        return userID;
    }

    public String getPassword() {
        return password;
    }
}