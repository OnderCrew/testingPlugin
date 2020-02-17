package xyz.ondercrew.testingplugin;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CommandHandler implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("twitch")) {
            if (args.length < 1) {
                sender.sendMessage(ChatColor.RED + "한개 이상의 인수가 필요합니다!\n사용법: /twitch <트위치ID>");
            } else {
                TwitchRequest twiReq = new TwitchRequest();
                return twiReq.TwitchCommand(sender, args);
            }
        }
        return true;
    }
}