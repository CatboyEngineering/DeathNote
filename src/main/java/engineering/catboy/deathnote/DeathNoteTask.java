package engineering.catboy.deathnote;

import org.bukkit.attribute.Attribute;
import org.bukkit.damage.DamageSource;
import org.bukkit.damage.DamageType;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

public class DeathNoteTask extends BukkitRunnable {
    private final String playerName;
    private final DeathNote plugin;
    private final boolean finalKill;

    DeathNoteTask(DeathNote _plugin, String _name, boolean kill) {
        plugin = _plugin;
        playerName = _name;
        finalKill = kill;
    }

    public void run() {
        Player player = plugin.getServer().getPlayer(playerName);

        if(player != null && player.isOnline()) {
            if(!player.hasPermission("deathnote.exempt")) {
                if(finalKill) {
                    player.setHealth(0);
                }else{
                    player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 20 * 10, 2, false, false, false));
                    player.addPotionEffect(new PotionEffect(PotionEffectType.SLOWNESS, 20 * 10, 3, false, false, false));

                    // Schedule a new task to kill the target
                    new DeathNoteTask(plugin, player.getName(), true).runTaskLater(plugin, 20 * 10);
                }
            }
        }
    }
}