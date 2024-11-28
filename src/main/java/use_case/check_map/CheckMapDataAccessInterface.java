package use_case.check_map;

import entity.Location;

public interface CheckMapDataAccessInterface {
    /**
     * Checks if the given location exists by name.
     */
    boolean existsByName(String locationName);
}
