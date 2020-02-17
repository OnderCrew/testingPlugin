package xyz.ondercrew.testingplugin;

import org.bukkit.plugin.java.JavaPlugin;

public class TestingPlugin extends JavaPlugin {
    @Override
    public void onEnable() {
        getLogger().info("Onder Crew Testing Plugin v1");

        this.getConfig().options().copyDefaults(true);
        this.saveConfig();

        this.getCommand("twitch").setExecutor(new CommandHandler());
        this.getCommand("nick").setExecutor(new CommandHandler());

        this.getServer().getPluginManager().registerEvents(new NicknameForcer(), this);
    }
}