package com.nextplugins.libs.hologramwrapper.impl;

import com.nextplugins.libs.hologramwrapper.HologramWrapper;
import com.nextplugins.libs.hologramwrapper.utils.ColorUtil;
import eu.decentsoftware.holograms.api.DHAPI;
import eu.decentsoftware.holograms.api.holograms.Hologram;
import org.bukkit.Location;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;

public class DecentHologramsWrapper implements HologramWrapper {

    private final Queue<String> holograms;

    public DecentHologramsWrapper(Plugin plugin) {
        this.holograms = new ConcurrentLinkedDeque<>();
    }

    public Queue<String> getHolograms() {
        return holograms;
    }

    @Override
    public Object create(Location location, List<String> lines) {
        final String name = "DHHW-" + pseudoRandomString();

        final Hologram hologram = DHAPI.createHologram(name, location, ColorUtil.of(lines));

        hologram.enable();
        holograms.add(name);

        return name;
    }

    @Override
    public void clear() {
        for (String name : holograms) {
            final Hologram hologram = DHAPI.getHologram(name);

            if (hologram == null) continue;

            hologram.delete();
            holograms.remove(name);
        }
    }

    @Override
    public void delete(Object id) {
        final String name = id.toString();
        final Hologram hologram = DHAPI.getHologram(name);

        if (hologram == null) return;

        hologram.delete();
        holograms.remove(name);
    }

    private String pseudoRandomString() {
        return Long.toHexString(System.currentTimeMillis());
    }

}
