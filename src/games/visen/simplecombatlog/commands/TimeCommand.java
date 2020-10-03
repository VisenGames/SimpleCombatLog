package games.visen.simplecombatlog.commands;

import games.visen.simplecombatlog.Main;
import games.visen.simplecombatlog.core.CombatPlayer;
import games.visen.simplecombatlog.utils.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import sun.nio.ch.Util;


public class TimeCommand implements CommandExecutor {

        private Main plugin;

        public TimeCommand(Main plugin) {
            this.plugin = plugin;

            plugin.getCommand("combattime").setExecutor(this);
        }

        @Override
        public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
            if(!(sender instanceof Player)) {
                sender.sendMessage("This command can only be executed by a player!");
                return false;
            }
            Player player = (Player) sender;
            CombatPlayer combatPlayer = CombatPlayer.getCombatPlayer(player);
            if(!combatPlayer.inCombat) {
                Utils.message(player, "&cYou are not in combat!");
                return true;
            }
            if(combatPlayer.getFormatedTime(true, combatPlayer.startTime, "combatTime") != null) {
                Utils.message(player, "&cTime remaining: " + combatPlayer.getFormatedTime(combatPlayer.inCombat, combatPlayer.startTime, "combatTime"));
            }
            return true;
    }

}
