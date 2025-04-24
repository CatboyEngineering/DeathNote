package engineering.catboy.deathnote;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerEditBookEvent;
import org.bukkit.inventory.meta.BookMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class DeathNoteListener implements Listener {
    private final String strikethrough = ChatColor.STRIKETHROUGH.toString();
    private final String reset = ChatColor.RESET.toString();

    DeathNote plugin;

    DeathNoteListener(DeathNote _plugin) {
        plugin = _plugin;
    }

    // TODO prevent anvil renaming

    @EventHandler
    public void onBookEditEvent(PlayerEditBookEvent event) {
        if(event.getPreviousBookMeta().getItemName().equals(plugin.noteTitle) && event.getPreviousBookMeta().getLore().get(0).equals(plugin.noteLore)) {
            if (event.isSigning()) {
                event.setSigning(false);
            }

            if (event.getPlayer().hasPermission("deathnote.use")) {
                List<String> pages = event.getNewBookMeta().getPages();
                List<String> newPages = new ArrayList<>();
                List<String> targets = new ArrayList<>();

                for (String page : pages) {
                    for (String line : page.split("\n")) {
                        if (!line.startsWith(strikethrough)) {
                            if (Pattern.matches("^[a-zA-Z0-9_]{3,16}$", line)) {
                                targets.add(line);
                            }
                        }
                    }

                    for (String target : targets) {
                        page = page.replace(target, strikethrough + target + reset);
                    }

                    newPages.add(page);
                }

                BookMeta newMeta = event.getNewBookMeta();

                newMeta.setPages(newPages);
                event.setNewBookMeta(newMeta);

                for(String target : targets) {
                    new DeathNoteTask(plugin, target, false).runTaskLater(plugin, 20 * 30);
                }
            }
        }
    }
}
