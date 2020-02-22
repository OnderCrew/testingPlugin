package xyz.ondercrew.testingplugin;

import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemHeldEvent;

public class PianoEvents implements Listener {
    @EventHandler
    public void onPlayerChangeSlot (PlayerItemHeldEvent event) {
        Player player = event.getPlayer();
        World world = player.getWorld();
        int slot = event.getNewSlot();
        switch (slot) {
            case 0:
                world.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_XYLOPHONE, 100, (float) 0.707107);
                break;

            case 1:
                world.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_XYLOPHONE, 100, (float) 0.793701);
                break;

            case 2:
                world.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_XYLOPHONE, 100, (float) 0.890899);
                break;

            case 3:
                world.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_XYLOPHONE, 100, (float) 0.943874);
                break;

            case 4:
                world.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_XYLOPHONE, 100, (float) 1.059463);
                break;

            case 5:
                world.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_XYLOPHONE, 100, (float) 1.189207);
                break;

            case 6:
                world.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_XYLOPHONE, 100, (float) 1.334840);
                break;

            case 7:
                world.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_XYLOPHONE, 100, (float) 1.414214);
                break;
        }
        player.getInventory().setHeldItemSlot(8);
    }
}
