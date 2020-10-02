package games.visen.simplecombatlog.utils;

import games.visen.simplecombatlog.Main;

public class TimeFormater {
    public static String formatTime(long starttime, long endtime) {
        String formatedTime = "";
        long totaltime = (Main.getPluginConfig().getInt("combatTime") * 1000) - (endtime - starttime);
        long seconds = totaltime / 1000;
        long hours = seconds / 3600;
        seconds -= hours * 3600;
        long minutes = seconds / 60;
        seconds -= minutes * 60;
        if(hours != 0) {
            formatedTime += hours + "h ";
        }
        if(minutes != 0) {
            formatedTime += minutes + "m ";
        }
        formatedTime += seconds + "s";

        return formatedTime;
    }
}
