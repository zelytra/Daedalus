package fr.zelytra.daedalus.commands.revive;

import fr.zelytra.daedalus.Daedalus;
import fr.zelytra.daedalus.managers.faction.Faction;
import fr.zelytra.daedalus.managers.game.settings.GameSettings;
import fr.zelytra.daedalus.managers.gods.GodsEnum;
import fr.zelytra.daedalus.utils.Message;
import fr.zelytra.daedalus.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.attribute.Attribute;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.jetbrains.annotations.NotNull;

public class HadesRevive implements CommandExecutor {
    public static boolean hadesHasRevive = false;

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player))
            return false;

        Player player = (Player) sender;
        Faction faction = Daedalus.getInstance().getGameManager().getFactionManager().getFactionOf(player);

        boolean canUseCommand = faction.getGodsEnum() == GodsEnum.HADES && faction.getGod() != null && faction.getGod().getName() == player.getName();


        if (!canUseCommand || hadesHasRevive) {
            player.sendMessage(Message.getPlayerPrefixe() + GameSettings.LANG.textOf("command.permissionDenied"));
            return false;
        }


        if (args.length == 1) {
            Player target = Bukkit.getPlayer(args[0]);
            if (reviveToExecutor(player, target))
                hadesHasRevive = true;

            return true;
        } else {
            player.sendMessage(Message.getHelp(cmd.getName()));
            return false;
        }
    }

    private boolean reviveToExecutor(Player executor, Player target) {

        if (target == null) {
            executor.sendMessage(Message.getPlayerPrefixe() + GameSettings.LANG.textOf("command.playerOffline"));
            return false;
        }

        Faction playerFaction = Daedalus.getInstance().getGameManager().getFactionManager().getFactionOf(target);
        if (playerFaction.isAlive(target)) {
            executor.sendMessage(Message.getPlayerPrefixe() + GameSettings.LANG.textOf("command.playerStillAlive"));
            return false;
        } else {

            for (Faction faction : Daedalus.getInstance().getGameManager().getFactionManager().getFactionList()) {
                if (playerFaction.getGodsEnum() == GodsEnum.HADES) break;
                if (faction.getGodsEnum() != null && faction.getGodsEnum() == GodsEnum.HADES) {
                    faction.add(target);
                    playerFaction = faction;
                }
            }

            target.setGameMode(GameMode.SURVIVAL);

            target.teleport(executor.getLocation());
            applyTeamEffect(target);
            target.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(10.0);

            for (Player p : Bukkit.getOnlinePlayers()) {
                p.playSound(p.getLocation(), Sound.ENTITY_WITCH_HURT, 2, 0.1f);
            }


            Bukkit.broadcastMessage(Message.getPlayerPrefixe() + playerFaction.getType().getChatColor() + target.getName() + GameSettings.LANG.textOf("command.hadesRevive"));
        }
        return true;
    }

    private void applyTeamEffect(Player player) {

        player.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(GodsEnum.HADES.getGod().teamHeart());


        if (GodsEnum.HADES.getGod().teamEffects() != null) {
            player.addPotionEffects(GodsEnum.HADES.getGod().teamEffects());
        }

        player.getInventory().setHelmet(Utils.EnchantedItemStack(Material.DIAMOND_HELMET, Enchantment.PROTECTION_ENVIRONMENTAL, 2));
        player.getInventory().setChestplate(Utils.EnchantedItemStack(Material.DIAMOND_CHESTPLATE, Enchantment.PROTECTION_ENVIRONMENTAL, 2));
        player.getInventory().setLeggings(Utils.EnchantedItemStack(Material.IRON_LEGGINGS, Enchantment.PROTECTION_ENVIRONMENTAL, 2));
        player.getInventory().setBoots(Utils.EnchantedItemStack(Material.IRON_BOOTS, Enchantment.PROTECTION_ENVIRONMENTAL, 2));

        player.getInventory().setItemInMainHand(Utils.EnchantedItemStack(Material.DIAMOND_SWORD, Enchantment.DAMAGE_ALL, 2));
        player.getInventory().setItemInOffHand(new ItemStack(Material.SHIELD));


        player.getInventory().addItem(Utils.EnchantedItemStack(Material.IRON_PICKAXE, Enchantment.DIG_SPEED, 2));
        player.getInventory().addItem(new ItemStack(Material.COOKED_BEEF, 20));
        player.getInventory().addItem(new ItemStack(Material.BOW));
        player.getInventory().addItem(new ItemStack(Material.ARROW, 16));

        player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 100, 9, true, true, true));
        player.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 100, 9, true, true, true));

    }
}
