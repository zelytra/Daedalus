package fr.zelytra.daedalus.commands.maze;


import fr.zelytra.daedalus.Daedalus;
import fr.zelytra.daedalus.managers.maze.MazeHandler;
import fr.zelytra.daedalus.utils.Message;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;


public class MazeCommands implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String msg, String[] args) {
        if (!(sender instanceof Player)) {
            System.out.println(Message.getConsolePrefixe() + "You cannot execute this command.");
            return false;
        }
        Player player = (Player) sender;
        if (args.length == 2 && args[0].equalsIgnoreCase("generateGrid")) {
            player.sendMessage(Message.getPlayerPrefixe() + "§cStarting generation...");
            Bukkit.getScheduler().runTaskAsynchronously(Daedalus.getInstance(), () -> {
                Location origin = player.getLocation().getBlock().getLocation().clone();
                origin.setY(player.getWorld().getHighestBlockYAt((int) origin.getX(), (int) origin.getZ()));
                MazeHandler maze = new MazeHandler(origin, Integer.parseInt(args[1]), true, Daedalus.getInstance().getStructureManager().getGeneratedList());
                maze.demoGenerateGrid();
            });

            return true;
        } else if (args.length == 2 && args[0].equalsIgnoreCase("generateMaze")) {
            player.sendMessage(Message.getPlayerPrefixe() + "§cStarting generation...");
            Bukkit.getScheduler().runTaskAsynchronously(Daedalus.getInstance(), () -> {
                Location origin = player.getLocation().getBlock().getLocation().clone();
                origin.setY(player.getWorld().getHighestBlockYAt((int) origin.getX(), (int) origin.getZ()));
                MazeHandler maze = new MazeHandler(origin, Integer.parseInt(args[1]), true, Daedalus.getInstance().getStructureManager().getGeneratedList());
                maze.demoGenerateMaze();
            });

            return true;
        } else if (args.length == 2 && args[0].equalsIgnoreCase("demoGenerateScaleMaze")) {
            player.sendMessage(Message.getPlayerPrefixe() + "§cStarting generation...");
            Bukkit.getScheduler().runTaskAsynchronously(Daedalus.getInstance(), () -> {
                Location origin = player.getLocation().getBlock().getLocation().clone();
                origin.setY(player.getWorld().getHighestBlockYAt((int) origin.getX(), (int) origin.getZ()));
                MazeHandler maze = new MazeHandler(origin, Integer.parseInt(args[1]), true, Daedalus.getInstance().getStructureManager().getGeneratedList());
                maze.demoGenerateScaleMaze();
            });

            return true;
        } else if (args.length == 1 && args[0].equalsIgnoreCase("generateScaleMaze")) {
            player.sendMessage(Message.getPlayerPrefixe() + "§cStarting generation...");
            Bukkit.getScheduler().runTaskAsynchronously(Daedalus.getInstance(), () -> {
                //Location origin = player.getLocation().getBlock().getLocation().clone();
                //origin.setY(player.getWorld().getHighestBlockYAt((int) origin.getX(), (int) origin.getZ()) + 1);
                Location origin = new Location(player.getWorld(),0,85,0);
                MazeHandler maze = new MazeHandler(origin, 300, true, Daedalus.getInstance().getStructureManager().getGeneratedList());
                maze.generateScaleMaze();
            });

            return true;
        } else if (args.length == 1 && args[0].equalsIgnoreCase("help")) {
            player.sendMessage("");
            player.sendMessage(Message.getPlayerPrefixe() + "§6Maze commands:");
            player.sendMessage("§6- /maze generateGrid §b[size]");
            player.sendMessage("§6- /maze generateMaze §b[size]");
            player.sendMessage("§6- /maze demoGenerateScaleMaze §b[size] [scale]");
            player.sendMessage("§6- /maze generateScaleMaze §b[size] [scale] [high]");
            player.sendMessage("");
            return true;
        } else {
            player.sendMessage(Message.getPlayerPrefixe() + "§cWrong syntax please refer to §6/maze help §ccommand.");
            return false;
        }

    }


}
