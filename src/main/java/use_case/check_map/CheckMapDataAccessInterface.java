package use_case.check_map;

import entity.Location;

/**
 * The interface of the DAO for the Check Map Use Case.
 */
public interface CheckMapDataAccessInterface {

    /**
     * Updates the system to display the location in the map.
     * @param location the location getting displayed
     * @throws DataAccessException the exception when the map data cannot be accessed correctly
     */
    void checkMap(Location location) throws DataAccessException;
}
