package games.visen.simplecombatlog.events;

import games.visen.simplecombatlog.Main;
import games.visen.simplecombatlog.core.CombatPlayer;
import games.visen.simplecombatlog.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;

public class PlayerItemConsumeEventL implements Listener {

    private Main plugin;

    public PlayerItemConsumeEventL(Main plugin) {
        this.plugin = plugin;
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onPlayerItemConsume(PlayerItemConsumeEvent event) {
        Player player = event.getPlayer();
        CombatPlayer combatPlayer = CombatPlayer.getCombatPlayer(player);
        ItemStack itemStack = event.getItem();
        if(itemStack.getType() == Material.GOLDEN_APPLE) {
            if(itemStack.getDurability() == 1) {
                if(combatPlayer.gappleCooldown) {
                    event.setCancelled(true);
                    Utils.message(player, "&cYou cant eat this for: " + combatPlayer.getFormatedTime(combatPlayer.gappleCooldown, combatPlayer.gappleTime, "gappleTime"));
                } else {
                    combatPlayer.setPlayerGapple();
                }
            }
        }
    }
}
