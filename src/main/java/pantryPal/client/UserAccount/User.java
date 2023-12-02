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

import org.bson.types.ObjectId;

public class User {
    private String username;
    private String password;

    public User(String username, String password) {
        this.username = username;
        this.password = Password.encryptPassword(password);
    }

    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }
}
