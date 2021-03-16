package fr.zelytra.daedalus.maze;

import fr.zelytra.daedalus.utils.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;

public class MazeTabCommands implements TabCompleter {
    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String msg, String[] args) {
        List<String> commandsList = new ArrayList<>();
        if (cmd.getName().equalsIgnoreCase("maze")) {
            if (args.length == 1) {
                commandsList.add("generateGrid");
                commandsList.add("generateMaze");
                commandsList.add("generateScaleMaze");
                commandsList.add("demoGenerateScaleMaze");
                commandsList.add("help");
                commandsList = Utils.dynamicTab(commandsList, args[0]);
            }
            if (args.length <= 2 && (args[0].equalsIgnoreCase("generateGrid")||args[0].equalsIgnoreCase("generateMaze"))) {
                commandsList.add("<size>");
            }
            if (args.length <= 3 && args[0].equalsIgnoreCase("demoGenerateScaleMaze")) {
                commandsList.add("<size> <scale>");
            }
            if (args.length <= 4 && args[0].equalsIgnoreCase("generateScaleMaze")) {
                commandsList.add("<size> <scale> <high>");
            }
        }
        return commandsList;
    }
}
