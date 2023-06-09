package me.teakivy.teakstweaksloader.util;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Logger {
    public static void log(LogLevel level, String message) {
        if (message == null) return;

        String prefix = "";
        switch (level) {
            case ERROR:
                prefix = "&8[&c&lERROR&r&8]";
                break;
            case WARNING:
                prefix = "&8[&6&lWARNING&r&8]";
                break;
            case INFO:
                prefix = "&8[&e&lINFO&r&8]";
                break;
            case SUCCESS:
                prefix = "&8[&a&lSUCCESS&r&8]";
                break;
            case OUTLINE:
                prefix = "&8[&7&lOUTLINE&r&8]";
                break;
        }

        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&8[&6&lTTL&r&8] " + prefix + " &f" + message));

        Bukkit.getOnlinePlayers().stream().filter(player -> player.hasPermission("teakstweaks.manage")).forEach(player -> player.sendMessage(ChatColor.YELLOW + message));
    }

    public enum LogLevel { ERROR, WARNING, INFO, SUCCESS, OUTLINE }
}