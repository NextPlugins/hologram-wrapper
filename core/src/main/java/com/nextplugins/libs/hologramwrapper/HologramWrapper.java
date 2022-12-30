package com.nextplugins.libs.hologramwrapper;

import org.bukkit.Location;

import java.util.List;

public interface HologramWrapper {

    void create(Location location, List<String> lines);
    void clear();

    default void create(Location location, String... lines) {
        create(location, List.of(lines));
    }

}
