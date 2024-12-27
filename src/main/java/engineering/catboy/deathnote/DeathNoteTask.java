package engineering.catboy.deathnote;

import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

public class DeathNoteTask extends BukkitRunnable {
    private final String playerName;
    private final DeathNote plugin;

    DeathNoteTask(DeathNote _plugin, String _name) {
        plugin = _plugin;
        playerName = _name;
    }

    public void run() {
        Player player = plugin.getServer().getPlayer(playerName);

        if(player != null && player.isOnline()) {
            if(!player.hasPermission("deathnote.exempt")) {
                player.setHealth(6d);
                player.setFireTicks(20 * 15);
                player.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 20 * 10, 8, false, false, false));
                player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 20 * 10, 2, false, false, false));
                player.addPotionEffect(new PotionEffect(PotionEffectType.SLOWNESS, 20 * 10, 3, false, false, false));
                player.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 20 * 10, 8, false, false, false));
            }
        }
    }
}