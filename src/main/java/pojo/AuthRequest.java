package pojo;

/**
 * Represents an authentication request containing a username and password.
 * This class is used to serialize authentication data.
 */
public class AuthRequest {

    private String username; // The username for authentication
    private String password; // The password for authentication

    /**
     * Default constructor for creating an empty AuthRequest object.
     */
    public AuthRequest() {
        // Default constructor
    }

    /**
     * Parameterized constructor for creating an AuthRequest object with specified username and password.
     * @param username The username for authentication.
     * @param password The password for authentication.
     */
    public AuthRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    /**
     * Retrieves the username for authentication.
     * @return The username as a String.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username for authentication.
     * @param username The username to set.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Retrieves the password for authentication.
     * @return The password as a String.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password for authentication.
     * @param password The password to set.
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
