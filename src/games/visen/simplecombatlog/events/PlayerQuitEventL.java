package games.visen.simplecombatlog.events;

import games.visen.simplecombatlog.Main;
import games.visen.simplecombatlog.core.CombatPlayer;
import games.visen.simplecombatlog.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuitEventL implements Listener {

    private Main plugin;

    public PlayerQuitEventL(Main plugin) {
        this.plugin = plugin;
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        CombatPlayer combatPlayer = CombatPlayer.getCombatPlayer(player);
        if(combatPlayer != null) {
            if(combatPlayer.inCombat) {
                player.setHealth(0);
                Bukkit.broadcastMessage(Utils.color(Main.getPluginConfig().getString("broadcastMessage").replace("%player%", player.getName())));
            }
        }
    }
}
