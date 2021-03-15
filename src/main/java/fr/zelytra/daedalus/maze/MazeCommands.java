package fr.zelytra.daedalus.maze;


import fr.zelytra.daedalus.Daedalus;
import fr.zelytra.daedalus.utils.Message;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.util.BoundingBox;

import java.util.ArrayList;


public class MazeCommands implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {
        if (!(sender instanceof Player)) {
            System.out.println(Message.getConsolePrefixe() + "You cannot execute this command.");
            return false;
        }
        Player player = (Player) sender;
        if (args.length == 2 && args[0].equalsIgnoreCase("generateGrid")) {
            player.sendMessage(Message.getPlayerPrefixe() + "§cStarting generation...");
            Bukkit.getScheduler().runTaskAsynchronously(Daedalus.plugin, () -> {
                Location origin = player.getLocation().clone();
                origin.setY(player.getWorld().getHighestBlockYAt((int) origin.getX(), (int) origin.getZ()));
                MazeHandler maze = new MazeHandler(origin, Integer.parseInt(args[1]), false);
                maze.demoGenerateGrid();
            });

            return true;
        }

        if (args.length == 2 && args[0].equalsIgnoreCase("generateMaze")) {
            player.sendMessage(Message.getPlayerPrefixe() + "§cStarting generation...");
            Bukkit.getScheduler().runTaskAsynchronously(Daedalus.plugin, () -> {
                /*
                ArrayList<BoundingBox> land = new ArrayList<>();
                land.add(new BoundingBox(11.0, player.getLocation().getY(), 11.0, 22.0, player.getLocation().getY(), 18.0));
                land.add(new BoundingBox(21.0, player.getLocation().getY(), 21.0, 32.0, player.getLocation().getY(), 36.0));*/
                Location origin = player.getLocation().clone();
                origin.setY(player.getWorld().getHighestBlockYAt((int) origin.getX(), (int) origin.getZ()));
                MazeHandler maze = new MazeHandler(origin, Integer.parseInt(args[1]), false);
                maze.demoGenerateMaze();
            });

            return true;
        }

        if (args.length == 2 && args[0].equalsIgnoreCase("generateScaleMaze")) {
            player.sendMessage(Message.getPlayerPrefixe() + "§cStarting generation...");
            Bukkit.getScheduler().runTaskAsynchronously(Daedalus.plugin, () -> {

                ArrayList<BoundingBox> land = new ArrayList<>();
                land.add(new BoundingBox(11.0, player.getLocation().getY(), 11.0, 22.0, player.getLocation().getY(), 18.0));
                land.add(new BoundingBox(21.0, player.getLocation().getY(), 21.0, 32.0, player.getLocation().getY(), 36.0));
                Location origin = player.getLocation().clone();
                origin.setY(player.getWorld().getHighestBlockYAt((int) origin.getX(), (int) origin.getZ()));
                MazeHandler maze = new MazeHandler(origin, Integer.parseInt(args[1]), false, 9, 20, land);
                maze.demoGenerateScaleMaze();
            });

            return true;
        }

        return false;
    }


}
