package me.teakivy.teakstweaksloader;

import me.teakivy.teakstweaksloader.util.FileDownloader;
import me.teakivy.teakstweaksloader.util.Logger;
import org.bukkit.Bukkit;
import org.bukkit.plugin.InvalidDescriptionException;
import org.bukkit.plugin.InvalidPluginException;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

public final class TeaksTweaksLoader extends JavaPlugin {

    @Override
    public void onEnable() {
        Plugin plugin = Bukkit.getPluginManager().getPlugin("TeaksTweaks");

        if (plugin != null) {
            Logger.log(Logger.LogLevel.INFO, "Disabling Teak's Tweaks...");
            Bukkit.getPluginManager().disablePlugin(plugin);
        }

        Bukkit.getScheduler().runTaskLater(this, () -> {
            File file = new File("plugins/TeaksTweaks.jar");
            if (file.exists()) {
                Logger.log(Logger.LogLevel.INFO, "Deleting TeaksTweaks.jar...");
                file.delete();
            }

            try {
                Logger.log(Logger.LogLevel.INFO, "Downloading TeaksTweaks.jar...");
                FileDownloader.downloadFile("https://api.spiget.org/v2/resources/94021/download", "plugins/TeaksTweaks.jar");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            try {
                Logger.log(Logger.LogLevel.INFO, "Loading TeaksTweaks.jar...");
                Bukkit.getPluginManager().loadPlugin(new File("plugins/TeaksTweaks.jar"));
            } catch (InvalidPluginException | InvalidDescriptionException e) {
                e.printStackTrace();
            }

            Plugin teaksTweaks = Bukkit.getPluginManager().getPlugin("TeaksTweaks");
            Logger.log(Logger.LogLevel.INFO, "Enabling TeaksTweaks.jar...");
            Bukkit.getPluginManager().enablePlugin(teaksTweaks);
        }, 20L * 2);
    }

    @Override
    public void onDisable() {
    }
}
