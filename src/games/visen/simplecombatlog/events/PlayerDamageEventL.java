package games.visen.simplecombatlog.events;

import games.visen.simplecombatlog.Main;
import games.visen.simplecombatlog.core.CombatPlayer;
import games.visen.simplecombatlog.utils.Utils;
import org.bukkit.Bukkit;
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
            if(event.getDamager() instanceof Player) {
                Player damager = (Player) event.getDamager();
                CombatPlayer combatPlayer = CombatPlayer.getCombatPlayer(player);
                if(combatPlayer == null) {
                    combatPlayer = new CombatPlayer(player);
                    Main.combatPlayers.add(combatPlayer);
                }
                combatPlayer.setPlayerCombat();
                Utils.message(player, Main.getPluginConfig().getString("inCombatMessage").replace("%player%", damager.getName()));
                Utils.message(damager, Main.getPluginConfig().getString("tagCombatMessage").replace("%player%", combatPlayer.player.getName()));
            }
        }
    }
}
