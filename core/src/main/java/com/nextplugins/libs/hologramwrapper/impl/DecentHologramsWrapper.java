package com.nextplugins.libs.hologramwrapper.impl;

import com.nextplugins.libs.hologramwrapper.HologramWrapper;
import com.nextplugins.libs.hologramwrapper.utils.ColorUtil;
import eu.decentsoftware.holograms.api.DHAPI;
import eu.decentsoftware.holograms.api.holograms.Hologram;
import org.bukkit.Location;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

public class DecentHologramsWrapper implements HologramWrapper {

    private final Plugin plugin;
    private final List<String> holograms;

    public DecentHologramsWrapper(Plugin plugin) {
        this.plugin = plugin;
        this.holograms = new ArrayList<>();
    }

    public List<String> getHolograms() {
        return holograms;
    }

    @Override
    public void create(Location location, List<String> lines) {
        final String name = "DHHW-" + pseudoRandomString();

        final Hologram hologram = DHAPI.createHologram(name, location, ColorUtil.of(lines));

        hologram.enable();
        holograms.add(name);
    }

    @Override
    public void clear() {
        for (String name : holograms) {
            final Hologram hologram = DHAPI.getHologram(name);

            if (hologram == null) continue;

            hologram.delete();
        }
    }

    private String pseudoRandomString() {
        return Long.toHexString(System.currentTimeMillis());
    }

}
