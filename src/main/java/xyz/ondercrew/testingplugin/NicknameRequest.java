package xyz.ondercrew.testingplugin;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class NicknameRequest {
    public boolean nicknameCommand (CommandSender sender, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            player.setDisplayName(args[0]);
            sender.sendMessage(ChatColor.GREEN + "닉네임이 \"" + ChatColor.BOLD + args[0] + ChatColor.RESET + ChatColor.GREEN + "\"로 설정되었습니다!");
        } else {
            sender.sendMessage(ChatColor.RED + "플레이어만 이 명령어를 사용 할 수 있습니다");
        }
        return true;
    }
}
