/**
 * User.java should handle the logic for a user, holding information such as 
 * the username and id . In terms of actually managing user information:
 * 
 * MongoDB to store user information per user (id, username) in "users"
 * collection
 * 
 * Note: user should not store any data with passwords. only database needs to
 * store it.
 */

package pantryPal.client.UserAccount;

public class User {
    private String userID;
    private String username;
    private String password;
    Password p = new Password();

    User(String userID, String username, String password) {
        this.username = username;
        this.password = p.encryptPassword(password);
    }

    public String getUserID() {
        return userID;
    }
    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }
}
