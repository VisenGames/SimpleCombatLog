package games.visen.simplecombatlog.events;

import games.visen.simplecombatlog.Main;
import games.visen.simplecombatlog.core.CombatPlayer;
import games.visen.simplecombatlog.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class PlayerDamageEventL implements Listener {

    private Main plugin;

    public PlayerDamageEventL(Main plugin) {
        this.plugin = plugin;
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onPlayerDamage(EntityDamageByEntityEvent event) {
        if(event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();
            Player damager;
            if(event.getDamager() instanceof Player) {
                damager = (Player) event.getDamager();
            } else if(event.getDamager() instanceof Arrow) {
                Arrow arrow = (Arrow) event.getDamager();
                if(arrow.getShooter() instanceof Player) {
                    damager = (Player) arrow.getShooter();
                } else {
                    return;
                }
            } else {
                return;
            }
            CombatPlayer combatPlayer = CombatPlayer.getCombatPlayer(player);
            if(!combatPlayer.inCombat) {
                Utils.message(player, Main.getPluginConfig().getString("inCombatMessage").replace("%player%", damager.getName()));
                Utils.message(damager, Main.getPluginConfig().getString("tagCombatMessage").replace("%player%", combatPlayer.player.getName()));
            }
            combatPlayer.setPlayerCombat();
        }
    }
}
