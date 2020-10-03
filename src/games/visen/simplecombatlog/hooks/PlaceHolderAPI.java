package games.visen.simplecombatlog.hooks;

import games.visen.simplecombatlog.Main;
import games.visen.simplecombatlog.core.CombatPlayer;
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
            return combatPlayer.getFormatedTime(combatPlayer.inCombat, combatPlayer.startTime, "combatTime");
        }

        if(identifier.equals("gapple")) {
            return combatPlayer.getFormatedTime(combatPlayer.gappleCooldown, combatPlayer.gappleTime, "gappleTime");
        }

        if(identifier.equals("pearl")) {
            return combatPlayer.getFormatedTime(combatPlayer.enderPearlCooldown, combatPlayer.enderPearlTime, "pearlTime");
        }

        return "Not Found";
    }
}

