package com.nextplugins.libs.hologramwrapper;

import com.nextplugins.libs.hologramwrapper.impl.DecentHologramsWrapper;
import com.nextplugins.libs.hologramwrapper.impl.HolographicDisplaysWrapper;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.plugin.Plugin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class Holograms {

    public static final Map<String, Class<? extends HologramWrapper>> WRAPPERS;

    static {
        WRAPPERS = new HashMap<>() {{
            put("HolographicDisplays", HolographicDisplaysWrapper.class);
            put("DecentHolograms", DecentHologramsWrapper.class);
        }};
    }

    private final Plugin plugin;
    private final HologramWrapper wrapper;

    private Holograms(Plugin plugin, HologramWrapper wrapper) {
        this.plugin = plugin;
        this.wrapper = wrapper;
    }

    public static HologramWrapper findWrapper(Plugin plugin) {
        final List<String> dependencies = plugin.getDescription().getSoftDepend();

        for (String dependency : dependencies) {
            if (!WRAPPERS.containsKey(dependency)) continue;
            if (!Bukkit.getServer().getPluginManager().isPluginEnabled(dependency)) continue;

            try {
                return WRAPPERS.get(dependency).getDeclaredConstructor(Plugin.class).newInstance(plugin);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }

        return null;
    }

    public static Holograms get(Plugin plugin) {
        final HologramWrapper wrapper = findWrapper(plugin);

        if (wrapper == null) {
            plugin.getLogger().severe("Couldn't find any HologramWrapper.");

            return null;
        }

        return new Holograms(plugin, wrapper);
    }

    public Plugin getPlugin() {
        return plugin;
    }

    public HologramWrapper getWrapper() {
        return wrapper;
    }

    public Object create(Location location, List<String> lines) {
        return wrapper.create(location, lines);
    }

    public Object create(Location location, String... lines) {
        return wrapper.create(location, lines);
    }

    public void clear() {
        wrapper.clear();
    }

}
