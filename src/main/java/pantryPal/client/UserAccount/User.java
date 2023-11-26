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
    private ObjectId userID;
    private String username;
    private String password;

    User(String username, String password) {
        this.userID = new ObjectId();
        this.username = username;
        this.password = Password.encryptPassword(password);
    }

    public ObjectId getUserID() {
        return userID;
    }
    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }
}
