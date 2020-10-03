package games.visen.simplecombatlog;

import games.visen.simplecombatlog.commands.TimeCommand;
import games.visen.simplecombatlog.core.CombatPlayer;
import games.visen.simplecombatlog.events.*;
import games.visen.simplecombatlog.hooks.PlaceHolderAPI;
import games.visen.simplecombatlog.utils.ActionbarAPI;
import games.visen.simplecombatlog.utils.Config;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.LinkedList;


public class Main extends JavaPlugin {

    public static LinkedList<CombatPlayer> combatPlayers = new LinkedList<>();

    private static Main instance;
    private static Config config;
    private static ActionbarAPI actionbarAPI;


    @Override
    public void onEnable() {
        instance = this;
        config = new Config("config.yml");
        actionbarAPI = new ActionbarAPI();
        new PlayerDamageEventL(this);
        new PlayerQuitEventL(this);
        new PlayerJoinEventL(this);
        new PlayerCommandPreprocessEventL(this);
        new PlayerItemConsumeEventL(this);
        new PlayerInteractEventL(this);
        new TimeCommand(this);
        new PlaceHolderAPI(this).register();

    }

    public static Main getInstance() { return instance; }

    public static Config getPluginConfig() { return config; }

    public static ActionbarAPI getActionbarAPI() { return actionbarAPI; }
}
