package fr.zelytra.daedalus.commands.revive;

import fr.zelytra.daedalus.Daedalus;
import fr.zelytra.daedalus.managers.faction.Faction;
import fr.zelytra.daedalus.managers.faction.PlayerStatus;
import fr.zelytra.daedalus.managers.gods.GodsEnum;
import fr.zelytra.daedalus.utils.Message;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Sound;
import org.bukkit.attribute.Attribute;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
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
            player.sendMessage(Message.getPlayerPrefixe() + "§cYou don't have permission to perform this command");
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
            executor.sendMessage(Message.getPlayerPrefixe() + "§cThis player is not online");
            return false;
        }

        Faction playerFaction = Daedalus.getInstance().getGameManager().getFactionManager().getFactionOf(target);
        if (playerFaction.isAlive(target)) {
            executor.sendMessage(Message.getPlayerPrefixe() + "§cThis player is still alive");
            return false;
        } else {
            playerFaction.setPlayerStatus(target, PlayerStatus.ALIVE);
            target.setGameMode(GameMode.SURVIVAL);

            target.teleport(executor.getLocation());
            applyTeamEffect(target);

            for (Player p : Bukkit.getOnlinePlayers()) {
                p.playSound(p.getLocation(), Sound.ENTITY_WITCH_HURT, 2, 0.1f);
            }

            for (Player p : Bukkit.getOnlinePlayers()) {
                Faction faction = Daedalus.getInstance().getGameManager().getFactionManager().getFactionOf(p);
                if (faction.getGodsEnum() == GodsEnum.HADES && faction.getGod() != null) {
                    faction.add(target);
                    playerFaction = faction;
                }
            }

            Bukkit.broadcastMessage(Message.getPlayerPrefixe() + playerFaction.getType().getChatColor() + target.getName() + "§6 has been revived (Hades)");
        }
        return true;
    }

    private void applyTeamEffect(Player player) {

        player.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(GodsEnum.HADES.getGod().teamHeart());


        if (GodsEnum.HADES.getGod().teamEffects() != null) {
            player.addPotionEffects(GodsEnum.HADES.getGod().teamEffects());
        }

    }
}
