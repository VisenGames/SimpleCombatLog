package games.visen.simplecombatlog.hooks;

import games.visen.simplecombatlog.Main;
import games.visen.simplecombatlog.core.CombatPlayer;
import games.visen.simplecombatlog.utils.Utils;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.entity.Player;

public class PlaceHolderAPI extends PlaceholderExpansion {

    private Main plugin;

    public PlaceHolderAPI(Main plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean persist() {
        return true;
    }

    @Override
    public boolean canRegister() {
        return true;
    }

    @Override
    public String getIdentifier() {
        return "simpleCombatLog";
    }

    @Override
    public String getAuthor() {
        return plugin.getDescription().getAuthors().toString();
    }

    @Override
    public String getVersion() {
        return plugin.getDescription().getVersion();
    }

    // %simpleCombatLog_time%
    @Override
    public String onPlaceholderRequest(Player player, String identifier) {
        if(player == null || !player.isOnline()) return "Player Not Found";
        CombatPlayer combatPlayer = CombatPlayer.getCombatPlayer(player);

        if(identifier.equals("time")) {
            if(combatPlayer.inCombat) {
                return combatPlayer.getFormatedTime(true, combatPlayer.startTime, "combatTime");
            }
            return "Not in combat!";
        }

        if(identifier.equals("gapple")) {
            if(combatPlayer.gappleCooldown) {
                return combatPlayer.getFormatedTime(true, combatPlayer.gappleTime, "gappleTime");
            }
            return Utils.color("&aAvailable");
        }

        if(identifier.equals("pearl")) {
            if(combatPlayer.enderPearlCooldown) {
                return combatPlayer.getFormatedTime(true, combatPlayer.enderPearlTime, "pearlTime");
            }
            return Utils.color("&aAvailable");
        }

        return "Not Found";
    }
}

