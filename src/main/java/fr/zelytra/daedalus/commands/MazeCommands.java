package fr.zelytra.daedalus.commands;


import fr.zelytra.daedalus.Daedalus;
import fr.zelytra.daedalus.maze.MazeHandler;
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
            Bukkit.getScheduler().runTaskAsynchronously(Daedalus.getInstance(), () -> {
                Location origin = player.getLocation().clone();
                origin.setY(player.getWorld().getHighestBlockYAt((int) origin.getX(), (int) origin.getZ()));
                MazeHandler maze = new MazeHandler(origin, Integer.parseInt(args[1]), false);
                maze.demoGenerateGrid();
            });

            return true;
        }

        else if (args.length == 2 && args[0].equalsIgnoreCase("generateMaze")) {
            player.sendMessage(Message.getPlayerPrefixe() + "§cStarting generation...");
            Bukkit.getScheduler().runTaskAsynchronously(Daedalus.getInstance(), () -> {
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

        else if (args.length == 3 && args[0].equalsIgnoreCase("demoGenerateScaleMaze")) {
            int scale = Integer.parseInt(args[2]);
            player.sendMessage(Message.getPlayerPrefixe() + "§cStarting generation...");
            Bukkit.getScheduler().runTaskAsynchronously(Daedalus.getInstance(), () -> {
                ArrayList<BoundingBox> land = new ArrayList<>();
                land.add(new BoundingBox(11.0, player.getLocation().getY(), 11.0, 22.0, player.getLocation().getY(), 18.0));
                land.add(new BoundingBox(21.0, player.getLocation().getY(), 21.0, 32.0, player.getLocation().getY(), 36.0));
                Location origin = player.getLocation().clone();
                origin.setY(player.getWorld().getHighestBlockYAt((int) origin.getX(), (int) origin.getZ()));
                MazeHandler maze = new MazeHandler(origin, Integer.parseInt(args[1]), false, scale,land);
                maze.demoGenerateScaleMaze();
            });

            return true;
        }

        else if (args.length == 4 && args[0].equalsIgnoreCase("generateScaleMaze")) {
            int scale = Integer.parseInt(args[2]);
            int height = Integer.parseInt(args[3]);
            player.sendMessage(Message.getPlayerPrefixe() + "§cStarting generation...");
            Bukkit.getScheduler().runTaskAsynchronously(Daedalus.getInstance(), () -> {
                Location origin = player.getLocation().clone();
                origin.setY(player.getWorld().getHighestBlockYAt((int) origin.getX(), (int) origin.getZ()));
                MazeHandler maze = new MazeHandler(origin, Integer.parseInt(args[1]), false, scale, height);
                maze.generateScaleMaze();
            });

            return true;
        }
        else if (args.length == 1 && args[0].equalsIgnoreCase("help")) {
            player.sendMessage("");
            player.sendMessage(Message.getPlayerPrefixe() + "§6Maze commands:");
            player.sendMessage("§6- /maze generateGrid §b[size]");
            player.sendMessage("§6- /maze generateMaze §b[size]");
            player.sendMessage("§6- /maze demoGenerateScaleMaze §b[size] [scale]");
            player.sendMessage("§6- /maze generateScaleMaze §b[size] [scale] [high]");
            player.sendMessage("");
            return true;
        }

        else {
            player.sendMessage(Message.getPlayerPrefixe() + "§cWrong syntax please refer to §6/maze help §ccommand.");
            return false;
        }

    }


}
