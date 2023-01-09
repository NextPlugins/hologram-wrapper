package com.nextplugins.libs.hologramwrapper;

import org.bukkit.Location;

import java.util.List;

public interface HologramWrapper {

    /**
     * Creates the hologram
     *
     * @param location the target location
     * @param lines the lines
     * @return the hologram id
     */
    Object create(Location location, List<String> lines);

    /**
     * Deletes all holograms created through the wrapper or its API
     */
    void clear();

    /**
     * Delete a specific hologram
     * @param id the hologram id, provided on HologramWrapper#create
     */
    void delete(Object id);

    /**
     * Creates a hologram, but with array instead of list
     * @param location the target location
     * @param lines the lines
     * @return the hologram id
     */
    default Object create(Location location, String... lines) {
        return create(location, List.of(lines));
    }

}
