package xyz.ondercrew.testingplugin;

import org.bukkit.plugin.java.JavaPlugin;

public class TestingPlugin extends JavaPlugin {
    @Override
    public void onEnable() {
        this.getLogger().info("Onder Crew Testing Plugin v1");
        this.getServer().getPluginManager().registerEvents(new PianoEvents(), this);
    }
}
