package fr.zelytra.daedalus.commands.item;

import fr.zelytra.daedalus.managers.items.CustomItemStack;
import fr.zelytra.daedalus.managers.items.CustomMaterial;
import fr.zelytra.daedalus.utils.Message;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class ItemsCommands implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player)) {
            return false;
        }
        Player player = (Player) sender;
        if (args.length == 1) {
            if (CustomMaterial.getByName(args[0]) != null) {
                CustomItemStack cItem = new CustomItemStack(CustomMaterial.getByName(args[0]), 1);
                player.getInventory().addItem(cItem.getItem());
                player.sendMessage(Message.getPlayerPrefixe() + "§aGave 1 §6§l" + args[0] + "§r§a to " + player.getName());
                return true;
            } else {
                player.sendMessage(Message.getPlayerPrefixe() + "§cThis item doesn't exist.");
                return false;
            }
        } else {
            player.sendMessage(Message.getPlayerPrefixe() + "§cWrong command syntax.");
            return false;
        }

    }
}
