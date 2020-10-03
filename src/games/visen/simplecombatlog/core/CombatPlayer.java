package games.visen.simplecombatlog.core;

import games.visen.simplecombatlog.Main;
import games.visen.simplecombatlog.utils.TimeFormater;
import games.visen.simplecombatlog.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;

public class CombatPlayer {

    public BukkitTask task;

    public BukkitTask gappleTask;

    public BukkitTask pearlTask;

    public Player player;

    public OfflinePlayer offlinePlayer;

    public long gappleTime = -1;

    public long enderPearlTime = -1;

    public long startTime = -1;

    public boolean inCombat = false;

    public boolean gappleCooldown = false;

    public boolean enderPearlCooldown = false;

    public CombatPlayer(Player p) {
        this.player = p;
        this.offlinePlayer = Bukkit.getOfflinePlayer(p.getUniqueId());
    }

    public String getFormatedTime(boolean condition, long st, String ct) {
        if(condition) {
            if(st >= 0){
                return TimeFormater.formatTime(st, System.currentTimeMillis(), ct);
            }
        }
        return null;
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
            Utils.message(this.player, "&cYou are no longer in combat!");
        }, 20 * Main.getPluginConfig().getInt("combatTime"));
    }

    public void setPlayerGapple() {
        this.gappleCooldown = true;
        this.gappleTime = System.currentTimeMillis();
        if(gappleTask != null) {
            gappleTask.cancel();
        }
        gappleTask = Bukkit.getScheduler().runTaskLater(Main.getInstance(), () -> {
            this.gappleCooldown = false;
            this.gappleTime = -1;
            Utils.message(this.player, "&cYou can eat gapples again!");
        }, 20 * Main.getPluginConfig().getInt("gappleTime"));
    }

    public void setPlayerPearl() {
        this.enderPearlCooldown = true;
        this.enderPearlTime = System.currentTimeMillis();
        if(pearlTask != null) {
            pearlTask.cancel();
        }
        pearlTask = Bukkit.getScheduler().runTaskLater(Main.getInstance(), () -> {
            this.enderPearlCooldown = false;
            this.enderPearlTime = -1;
            Utils.message(this.player, "&cYou can throw pearls again!");
        }, 20 * Main.getPluginConfig().getInt("pearlTime"));
    }

    public static CombatPlayer getCombatPlayer(Player p) {
        for(CombatPlayer cp : Main.combatPlayers) if(cp.player == p) return cp;

        CombatPlayer player = new CombatPlayer(p);
        Main.combatPlayers.add(player);
        return player;
    }
}
