package games.visen.simplecombatlog.events;

import games.visen.simplecombatlog.Main;
import games.visen.simplecombatlog.core.CombatPlayer;
import games.visen.simplecombatlog.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class PlayerInteractEventL implements Listener {

    private Main plugin;

    public PlayerInteractEventL(Main plugin) {
        this.plugin = plugin;
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        if(event.getAction().equals(Action.RIGHT_CLICK_AIR) || event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
            Player player = event.getPlayer();
            CombatPlayer combatPlayer = CombatPlayer.getCombatPlayer(player);
            if(player.getItemInHand() != null) {
                if(player.getItemInHand().getType().equals(Material.ENDER_PEARL)) {
                    if(combatPlayer.enderPearlCooldown) {
                        event.setCancelled(true);
                        Utils.message(player, "&cYou cant use this for: " + combatPlayer.getFormatedTime(combatPlayer.enderPearlCooldown, combatPlayer.enderPearlTime, "pearlTime"));
                    } else {
                        if(!player.hasPermission("simplecombatlog.bypass")) {
                            combatPlayer.setPlayerPearl();
                        }
                    }
                }
            }
        }
    }
}
