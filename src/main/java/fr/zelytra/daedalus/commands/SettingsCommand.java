package fr.zelytra.daedalus.commands;

import fr.zelytra.daedalus.builders.InventoryBuilder;
import fr.zelytra.daedalus.builders.ItemBuilder;
import fr.zelytra.daedalus.managers.game.settings.GameSettings;
import fr.zelytra.daedalus.managers.game.settings.TemplesGenerationEnum;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class SettingsCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {

        if (sender instanceof Player) {

            Player p = (Player) sender;

            if(args.length > 0){

                p.sendMessage("§cTo many arguments !\n§cPlease use the command with no arguments: §7/settings");
                return false;

            }

            if(p.isOp()){

                Inventory inv = new InventoryBuilder("§3Game settings", 27).getInventory();

                if(GameSettings.GOD_SELECTION == TemplesGenerationEnum.RANDOM){
                    inv.setItem(12, new ItemBuilder(Material.PISTON, "§6Temples generation").getGenerationSelection());
                }else{
                    inv.setItem(10, new ItemBuilder(Material.TOTEM_OF_UNDYING, "§6Gods selection", "", "§8CLICK TO SELECT").getItemStack());
                    inv.setItem(12, new ItemBuilder(Material.STICKY_PISTON, "§6Temples generation").getGenerationSelection());

                }

                p.openInventory(inv);

                return true;
            }else{
                p.sendMessage("§cYou don't have the permission to configure the game !");
                return false;
            }
        }

        return false;
    }
}
