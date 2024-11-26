package use_case.change_password;

import entity.User;

public interface ChangePasswordUserDataAccessInterface {
    /**
     * Updates the system to record this user's password.
     * @param user the user whose password is to be updated
     */
    void changePassword(User user);
}
