package games.visen.simplecombatlog.events;

import games.visen.simplecombatlog.Main;
import games.visen.simplecombatlog.core.CombatPlayer;
import games.visen.simplecombatlog.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class PlayerCommandPreprocessEventL implements Listener {

    private Main plugin;

    public PlayerCommandPreprocessEventL(Main plugin) {
        this.plugin = plugin;
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onCommand(PlayerCommandPreprocessEvent event) {
        Player player = event.getPlayer();
        CombatPlayer combatPlayer = CombatPlayer.getCombatPlayer(player);
        if(combatPlayer == null) {
            combatPlayer = new CombatPlayer(player);
        }
        if(combatPlayer.inCombat) {
            boolean isAllowed = false;
            for(String cmd : Main.getPluginConfig().getStringList("allowedCommands")) {
                if(event.getMessage().contains(cmd)) {
                    isAllowed = true;
                }
            }
            if(!isAllowed && !(player.hasPermission("simplecombatlog.bypass"))) {
                Utils.message(player, "&cYou cant use that command while in combat!");
                event.setCancelled(true);
            }
        }
    }
}
