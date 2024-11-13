package engineering.catboy.deathnote;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemRarity;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;
import java.util.logging.Level;

public class DeathNote extends JavaPlugin {
    public final String noteTitle = ChatColor.translateAlternateColorCodes('&', "&8Death Note");
    public final String noteLore = ChatColor.translateAlternateColorCodes('&', "&7&oA weathered, dusty old notebook");

    public void onEnable() {
        this.getLogger().log(Level.INFO, "DeathNote is loading...");

        configureCommands();
        this.getServer().getPluginManager().registerEvents(new DeathNoteListener(this), this);

        this.getLogger().log(Level.INFO, "DeathNote loading succeeded!");
        this.getLogger().log(Level.INFO, "If you like this plugin, please consider reviewing and donating!");
    }

    public ItemStack getDeathNote() {
        ItemStack note = new ItemStack(Material.WRITABLE_BOOK, 1);
        BookMeta noteMeta = (BookMeta)note.getItemMeta();

        noteMeta.setEnchantmentGlintOverride(true);
        noteMeta.setFireResistant(true);
        noteMeta.setItemName(noteTitle);
        noteMeta.setRarity(ItemRarity.EPIC);
        noteMeta.setLore(List.of(noteLore));
        noteMeta.setTitle("DeathNote");
        noteMeta.setGeneration(BookMeta.Generation.TATTERED);

        note.setItemMeta(noteMeta);

        return note;
    }

    private void configureCommands() {
        this.getCommand("deathnote").setExecutor(new DeathNoteCommandExecutor(this));
        this.getCommand("deathnote").setTabCompleter(new DeathNoteCommandTabCompleter());
    }
}