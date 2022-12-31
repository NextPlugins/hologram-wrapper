package com.nextplugins.libs.hologramwrapper.example;

import com.nextplugins.libs.hologramwrapper.Holograms;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class HologramWrapperPlugin extends JavaPlugin implements Listener {

    private final PluginManager pluginManager;

    public HologramWrapperPlugin() {
        pluginManager = getServer().getPluginManager();
    }

    private Holograms controller;

    @Override
    public void onEnable() {
        controller = Holograms.get(this);

        if (controller == null) {
            pluginManager.disablePlugin(this);

            return;
        }

        pluginManager.registerEvents(this, this);
    }

    @Override
    public void onDisable() {
        if (controller != null) controller.clear();
    }

    public Holograms getController() {
        return controller;
    }

    @EventHandler
    void onJoin(PlayerJoinEvent event) {
        final Player player = event.getPlayer();

        controller.create(player.getLocation(), "&aBem vindo!", player.getName());
    }

}
