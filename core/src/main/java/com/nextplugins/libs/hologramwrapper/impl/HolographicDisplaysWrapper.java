package com.nextplugins.libs.hologramwrapper.impl;

import com.gmail.filoghost.holographicdisplays.api.Hologram;
import com.gmail.filoghost.holographicdisplays.api.HologramsAPI;
import com.nextplugins.libs.hologramwrapper.HologramWrapper;
import com.nextplugins.libs.hologramwrapper.Holograms;
import com.nextplugins.libs.hologramwrapper.utils.ColorUtil;
import org.bukkit.Location;
import org.bukkit.plugin.Plugin;

import java.util.Collection;
import java.util.List;

public class HolographicDisplaysWrapper implements HologramWrapper {

    private final Plugin plugin;

    public HolographicDisplaysWrapper(Plugin plugin) {
        this.plugin = plugin;
    }

    public Plugin getPlugin() {
        return plugin;
    }

    @Override
    public Object create(Location location, List<String> lines) {
        final Hologram hologram = HologramsAPI.createHologram(plugin, location);

        ColorUtil.of(lines).forEach(hologram::appendTextLine);

        return hologram.getCreationTimestamp();
    }

    @Override
    public void clear() {
        HologramsAPI.getHolograms(plugin).forEach(Hologram::delete);
    }

    @Override
    public void delete(Object id) {
        final Collection<Hologram> holograms = HologramsAPI.getHolograms(plugin);
        final long createTimeStamp;

        try {
            createTimeStamp = Long.parseLong(id.toString());
        } catch (NumberFormatException exception) {
            return;
        }

        for (Hologram hologram : holograms) {
            if (hologram.isDeleted()) continue;
            if (hologram.getCreationTimestamp() != createTimeStamp) continue;

            hologram.delete();
            break;
        }
    }

}
