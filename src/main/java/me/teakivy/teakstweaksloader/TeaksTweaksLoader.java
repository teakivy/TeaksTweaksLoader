package me.teakivy.teakstweaksloader;

import me.teakivy.teakstweaksloader.util.FileUtils;
import me.teakivy.teakstweaksloader.util.Logger;
import me.teakivy.teakstweaksloader.util.PluginUnloader;
import org.bukkit.Bukkit;
import org.bukkit.plugin.InvalidDescriptionException;
import org.bukkit.plugin.InvalidPluginException;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

public final class TeaksTweaksLoader extends JavaPlugin {

    private String dlUrl = "https://api.spiget.org/v2/resources/94021/download";

    @Override
    public synchronized void onEnable() {
        Plugin plugin = Bukkit.getPluginManager().getPlugin("TeaksTweaks");

        if (plugin != null) {
            Logger.log(Logger.LogLevel.INFO, "Disabling Teak's Tweaks...");
            PluginUnloader.unload(plugin);
        }

        Logger.log(Logger.LogLevel.INFO, "Deleting TeaksTweaks.jar...");
        FileUtils.deleteTeaksTweaksPluginFiles();

        if (FileUtils.isDevUpdate()) {
            Logger.log(Logger.LogLevel.INFO, "Loading Development Branch...");
            dlUrl = "https://github.com/teakivy/TeaksTweaksLoader/releases/download/teakstweaksdev/TeaksTweaks.-.DEV.jar";

            FileUtils.deleteDevLoader();
        }

        try {

            Logger.log(Logger.LogLevel.INFO, "Downloading TeaksTweaks.jar...");
            FileUtils.downloadFile(dlUrl, "plugins/TeaksTweaks.jar");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            Logger.log(Logger.LogLevel.INFO, "Loading Teak's Tweaks.jar...");
            Bukkit.getPluginManager().loadPlugin(new File("plugins/TeaksTweaks.jar"));
        } catch (InvalidPluginException | InvalidDescriptionException e) {
            e.printStackTrace();
        }

        Plugin teaksTweaks = Bukkit.getPluginManager().getPlugin("TeaksTweaks");
        Logger.log(Logger.LogLevel.INFO, "Enabling Teak's Tweaks.jar...");

        if (teaksTweaks == null) {
            Logger.log(Logger.LogLevel.ERROR, "Teak's Tweaks.jar failed to load!");
            return;
        }

        Bukkit.getPluginManager().enablePlugin(teaksTweaks);
    }

    @Override
    public void onDisable() {
    }
}
