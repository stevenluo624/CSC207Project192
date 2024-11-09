package entity;
/**
 * A  implementation of the User interface which handles students reviwing things.
 */
public class StudentUser implements User{
    private final String name;
    private final String password;

    public StudentUser(String name, String password) {
        this.name = name;
        this.password = password;
    }
    @Override
    public String getUsername() {
        return name;
    }

    @Override
    public String getPassword() {
        return password;
    }
}
