package me.treexhd.mc.instantbreakinflight.instantbreakblockinflight;

import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public final class InstantBreakBlockinFlight extends JavaPlugin {
    private final InstantBreakBlockinFlightListener IBBFListener = new InstantBreakBlockinFlightListener();


    @Override
    public void onEnable() {
        // Plugin startup logic
        getServer().getPluginManager().registerEvents((Listener) IBBFListener, this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
