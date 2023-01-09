package com.nextplugins.libs.hologramwrapper;

import org.bukkit.Location;

import java.util.List;

public interface HologramWrapper {

    Object create(Location location, List<String> lines);
    void clear();
    void delete(Object id);

    default Object create(Location location, String... lines) {
        return create(location, List.of(lines));
    }

}
