package entity;

public interface LocationFactory {
    /**
     * Creates a new Location.
     * @param name the name of the new location
     * @return the new location
     */
    Location create(String name);
}
