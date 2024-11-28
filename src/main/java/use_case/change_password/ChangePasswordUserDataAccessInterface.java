package use_case.change_password;

import entity.User;

/**
 * The Data Access Interface for change password usecase.
 */
public interface ChangePasswordUserDataAccessInterface {
    /**
     * Updates the system to record this user's password.
     * @param user the user whose password is to be updated
     */
    void changePassword(User user);
}
