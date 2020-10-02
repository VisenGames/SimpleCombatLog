package games.visen.simplecombatlog.core;

import games.visen.simplecombatlog.Main;
import games.visen.simplecombatlog.utils.TimeFormater;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;

public class CombatPlayer {

    public BukkitTask task;

    public Player player;

    public OfflinePlayer offlinePlayer;

    public long startTime = -1;

    public boolean inCombat = false;

    public CombatPlayer(Player p) {
        this.player = p;
        this.offlinePlayer = Bukkit.getOfflinePlayer(p.getUniqueId());
    }

    public String getFormatedTime() {
        if(inCombat) {
            if(startTime >= 0){
                return TimeFormater.formatTime(startTime, System.currentTimeMillis());
            }
        }
        return "Not In Combat";
    }

    public void setPlayerCombat() {
        this.inCombat = true;
        this.startTime = System.currentTimeMillis();
        if(task != null) {
            task.cancel();
        }
        task = Bukkit.getScheduler().runTaskLater(Main.getInstance(), () -> {
            this.inCombat = false;
            this.startTime = -1;
        }, 20 * Main.getPluginConfig().getInt("combatTime"));
    }

    public static CombatPlayer getCombatPlayer(Player p) {
        for(CombatPlayer cp: Main.combatPlayers) {
            if(cp.player == p) {
                return cp;
            }
        }
        return null;
    }
}
