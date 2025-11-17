package dev.franki.eDEggBlocker;

import org.bukkit.plugin.java.JavaPlugin;

public final class EDEggBlocker extends JavaPlugin {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new EggBlockerListener(this), this);
        getLogger().info("Anti Drachenei-HS Plugin wurde gestartet");

    }

    @Override
    public void onDisable() {
        getLogger().info("Anti Drachenei-HS Plugin wurde gestoppt");
    }
}
