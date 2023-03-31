package me.teakivy.teakstweaksloader.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class FileUtils {
    public static void downloadFile(String fileURL, String saveDir) throws IOException {
        URL url = new URL(fileURL);
        BufferedInputStream in = new BufferedInputStream(url.openStream());
        FileOutputStream fileOutputStream = new FileOutputStream(saveDir);
        byte dataBuffer[] = new byte[1024];
        int bytesRead;
        while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
            fileOutputStream.write(dataBuffer, 0, bytesRead);
        }
        fileOutputStream.close();
    }



    public static List<File> getTeaksTweaksPluginFiles() {
        File pluginsFolder = new File("plugins");
        List<File> teaksTweaksPluginFiles = new ArrayList<>();

        if (!pluginsFolder.exists()) {
            return teaksTweaksPluginFiles;
        }

        File[] pluginFiles = pluginsFolder.listFiles();
        if (pluginFiles == null) {
            return teaksTweaksPluginFiles;
        }

        for (File pluginFile : pluginFiles) {
            if (pluginFile.isFile() &&
                    pluginFile.getName().contains("TeaksTweaks") &&
                    !pluginFile.getName().contains("Loader")) {
                teaksTweaksPluginFiles.add(pluginFile);
            }
        }

        return teaksTweaksPluginFiles;
    }

    public static void deleteTeaksTweaksPluginFiles() {
        List<File> teaksTweaksPluginFiles = getTeaksTweaksPluginFiles();
        for (File teaksTweaksPluginFile : teaksTweaksPluginFiles) {
            teaksTweaksPluginFile.delete();
        }
    }

    public static boolean isDevUpdate() {
        File devUpdateFile = new File("plugins/TeaksTweaks/dev.txt");
        return devUpdateFile.exists();
    }

    public static void deleteDevLoader() {
        File devLoader = new File("plugins/TeaksTweaks/dev.txt");
        devLoader.delete();
    }
}