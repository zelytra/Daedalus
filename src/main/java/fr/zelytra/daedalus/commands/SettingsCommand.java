package fr.zelytra.daedalus.commands;

import fr.zelytra.daedalus.builders.InventoryBuilder;
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

                p.sendMessage("§cNombre d'arguments excessif !\n§cVeuillez utilisez la commande comme indiqué: §7/settings");
                return false;

            }

            if(p.isOp()){

                Inventory inv = new InventoryBuilder("§3Paramètres de la partie", 9).getInventory();

                p.openInventory(inv);

                return true;
            }else{
                p.sendMessage("§cVous n'avez pas la permission de configurer la partie !");
                return false;
            }
        }

        return false;
    }
}
