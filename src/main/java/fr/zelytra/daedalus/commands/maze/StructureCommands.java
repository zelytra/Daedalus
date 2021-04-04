package fr.zelytra.daedalus.commands.maze;


import com.sk89q.worldedit.regions.Region;
import fr.zelytra.daedalus.managers.structure.WorldEditHandler;
import fr.zelytra.daedalus.utils.Message;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;


public class StructureCommands implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String msg, @NotNull String[] args) {
        if (!(sender instanceof Player)) {
            System.out.println(Message.getConsolePrefixe() + "You cannot execute this command.");
            return false;
        }
        Player player = (Player) sender;
        if (!Bukkit.getServer().getPluginManager().isPluginEnabled("WorldEdit")) {
            player.sendMessage(Message.getPlayerPrefixe() + "§cWorldEdit is needed on the server to perform this command.");
            return false;
        }
        if (args.length == 1 && args[0].equalsIgnoreCase("help")) {
            player.sendMessage("");
            player.sendMessage(Message.getPlayerPrefixe() + "§structure commands:");
            player.sendMessage("§6- /structure save §b[fileName]");
            player.sendMessage("");
            return true;

        } else if (args.length == 2 && args[0].equalsIgnoreCase("save")) {
            WorldEditHandler WEH = new WorldEditHandler(args[1], player);
            Region region = null;
            region = WEH.getSelection();

            if (region != null) {
                System.out.println(((region.getLength() + 1) / 8.0) % 1 + " " + ((region.getWidth() + 1) / 8.0) % 1);
                System.out.println(((region.getLength() + 1) / 8.0) % 2 + " " + ((region.getWidth() + 1) / 8.0) % 2);

                if (((region.getLength() + 1) / 8.0) % 2 == 0 || ((region.getWidth() + 1) / 8.0) % 2 == 0) {

                    System.out.println(((region.getLength() + 1) / 8.0) + " " + ((region.getWidth() + 1) / 8.0));
                    player.sendMessage(Message.getPlayerPrefixe() + "§cWrong structure size :§6 " + region.getWidth() + " / " + region.getLength());
                    return false;

                }
                if (((region.getLength() + 1) / 8.0) % 1 != 0 || ((region.getWidth() + 1) / 8.0) % 1 != 0) {
                    System.out.println(((region.getLength() + 1) / 8.0) + " " + ((region.getWidth() + 1) / 8.0));
                    player.sendMessage(Message.getPlayerPrefixe() + "§cWrong structure size :§6 " + region.getWidth() + " / " + region.getLength());
                    return false;
                }

            }

            if (WEH.saveStructure()) {
                player.sendMessage(Message.getPlayerPrefixe() + "§aStructure file saved :§6 " + WEH.getStructureName() + ".struct");
                return true;
            } else {
                player.sendMessage(Message.getPlayerPrefixe() + "§cPlease make a selection.");
                return false;
            }
        } else {
            player.sendMessage(Message.getPlayerPrefixe() + "§cWrong syntax please refer to §6/structure help §ccommand.");
            return false;
        }
    }
}



