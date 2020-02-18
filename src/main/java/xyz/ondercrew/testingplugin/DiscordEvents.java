package xyz.ondercrew.testingplugin;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class DiscordEvents extends ListenerAdapter {
    private FileConfiguration configFile = TestingPlugin.getPlugin(TestingPlugin.class).getConfig();

    @Override
    public void onMessageReceived (MessageReceivedEvent event) {
        if (event.getAuthor().isBot() || !event.isFromGuild()) return;
        if (!event.getChannel().getId().equals(configFile.getString("discord.channelId"))) return;
        String author = event.getAuthor().getName();
        String msg = event.getMessage().getContentRaw();

        if (event.getMember().getNickname() != null) author = event.getMember().getNickname();
        for (Player player : Bukkit.getOnlinePlayers()) {
            player.sendMessage(ChatColor.GRAY + "<" + author + "> " + msg);
        }
    }
}
