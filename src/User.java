public class User {
    String username;
    String password;
    boolean isFirstPurchase;

    User(String username, String password) {
        this.username = username;
        this.password = password;
        this.isFirstPurchase = true;
    }
}
