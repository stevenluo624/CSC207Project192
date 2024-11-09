package entity;
/**
 * The representation of a user in our program.
 */
public interface User {
    /**
     * Returns the username of the user.
     * @return username of user.
     */
    String getUsername();

    /**
     * Returns the password of the user.
     * @return password of the user.
     */
    String getPassword();
}
