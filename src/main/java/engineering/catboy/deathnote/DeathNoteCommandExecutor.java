package engineering.catboy.deathnote;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class DeathNoteCommandExecutor implements CommandExecutor {
    private final DeathNote plugin;
    private final String version;

    DeathNoteCommandExecutor(DeathNote _plugin) {
        plugin = _plugin;
        version = plugin.getDescription().getVersion();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (label.equalsIgnoreCase("deathnote")) {
            if (args.length == 0) {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&0&l>>>>>>>>>>>>>>> &8DeathNote &7" + version + " &0&l<<<<<<<<<<<<<<<"));

                if (sender.hasPermission("deathnote.get")) {
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&2/deathnote get &7- Get a new Death Note"));
                }
                if (sender.hasPermission("deathnote.reload")) {
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&2/deathnote reload &7- Reload the plugin configuration"));
                }

                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&0&l>>>>>>>>>>>>>>> &8DeathNote &7" + version + " &0&l<<<<<<<<<<<<<<<"));
            } else {
                if (args[0].equalsIgnoreCase("get")) {
                    if (sender.hasPermission("deathnote.get")) {
                        if (sender instanceof Player player) {
                            Location dropLocation = player.getLocation().clone();

                            dropLocation.setY(player.getWorld().getMaxHeight());
                            player.getWorld().dropItem(dropLocation, plugin.getDeathNote());
                            player.getWorld().strikeLightningEffect(dropLocation);

                            player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7&oA notebook falls from the sky..."));
                        } else {
                            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cYou must be a player to do that."));
                        }
                    }else{
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cYou don't have permission to do that."));
                    }
                } else if (args[0].equalsIgnoreCase("reload")) {
                    if (sender.hasPermission("deathnote.reload")) {
                        plugin.reloadConfig();
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aConfig was reloaded."));
                    }else{
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cYou don't have permission to do that."));
                    }
                } else {
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cThat command wasn't recognized."));
                }
            }

            return true;
        }
        return false;
    }
}