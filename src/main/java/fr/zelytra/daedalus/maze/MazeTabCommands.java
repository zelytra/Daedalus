package fr.zelytra.daedalus.maze;

import fr.zelytra.daedalus.utils.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.command.TabExecutor;

import java.util.ArrayList;
import java.util.List;

public class MazeTabCommands implements TabCompleter {
    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String msg, String[] args) {
        List<String> commandsList = new ArrayList<>();
        if(cmd.getName().equalsIgnoreCase("maze")){
            if(args.length==1){
                commandsList.add("test");
                commandsList.add("generate");
                commandsList = Utils.dynamicTab(commandsList,args[0]);
            }
        }
        return commandsList;
    }
}
