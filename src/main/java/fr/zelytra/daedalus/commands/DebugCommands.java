package fr.zelytra.daedalus.commands;

import fr.zelytra.daedalus.builders.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class DebugCommands implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if(!(sender instanceof Player)){
            return false;
        }
        Player player = (Player) sender;
        if(args.length==0){
        player.getInventory().setItem(0, new ItemBuilder(Material.WHITE_BANNER, "§7Team selection").getItemStack());
        player.getInventory().setItem(8, new ItemBuilder(Material.COMPARATOR, "§7Game settings").getSettings());
        return true;}
        else if(args.length==1){
            ArrayList<Block> sightBlock = (ArrayList<Block>) player.getLineOfSight(null, 50);
            for (Block block:sightBlock) {
                block.getLocation().getBlock().setType(Material.ACACIA_LOG);
            }
        }
        return false;
    }
}
