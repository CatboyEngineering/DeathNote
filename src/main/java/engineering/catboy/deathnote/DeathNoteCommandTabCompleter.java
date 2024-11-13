package engineering.catboy.deathnote;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.util.StringUtil;

import java.util.ArrayList;
import java.util.List;

public class DeathNoteCommandTabCompleter implements TabCompleter {
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        final List<String> possibleValues = getPossibleValues(sender);
        List<String> matches = new ArrayList<>();

        if(args.length > 0) {
            if(args.length == 1) {
                StringUtil.copyPartialMatches(args[0], possibleValues, matches);
            }
        }

        return matches;
    }

    private List<String> getPossibleValues(CommandSender sender) {
        List<String> values = new ArrayList<>();

        if (sender.hasPermission("deathnote.get")) {
            values.add("get");
        }

        if (sender.hasPermission("deathnote.reload")) {
            values.add("reload");
        }

        return values;
    }
}
