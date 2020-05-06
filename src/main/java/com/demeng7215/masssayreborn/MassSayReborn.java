package com.demeng7215.masssayreborn;

import com.demeng7215.demlib.DemLib;
import com.demeng7215.demlib.api.Common;
import com.demeng7215.demlib.api.DeveloperNotifications;
import com.demeng7215.demlib.api.Registerer;
import com.demeng7215.demlib.api.connections.SpigotUpdateChecker;
import com.demeng7215.demlib.api.files.CustomConfig;
import com.demeng7215.demlib.api.messages.MessageUtils;
import com.demeng7215.masssayreborn.commands.MassSayCmd;
import com.demeng7215.masssayreborn.commands.MassSayRebornCmd;
import org.bstats.bukkit.Metrics;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public final class MassSayReborn extends JavaPlugin {

  /* ERROR CODES
  1: Failed to load files.
   */

  private CustomConfig settingsFile;

  @Override
  public void onEnable() {

    DemLib.setPlugin(this);
    MessageUtils.setPrefix("&7[&bMassSay&7] &r");

    MessageUtils.console(
        "Beginning to enable MassSayReborn...\n\n"
            + "&b   _____                         _________             \n"
            + "&b  /     \\ _____    ______ ______/   _____/____  ___.__.\n"
            + "&b /  \\ /  \\\\__  \\  /  ___//  ___/\\_____  \\\\__  \\<   |  |\n"
            + "&3/    Y    \\/ __ \\_\\___ \\ \\___ \\ /        \\/ __ \\\\___  |\n"
            + "&3\\____|__  (____  /____  >____  >_______  (____  / ____|\n"
            + "&3        \\/     \\/     \\/     \\/        \\/     \\/\\/     \n");

    getLogger().info("Loading files...");
    try {
      settingsFile = new CustomConfig("settings.yml");
    } catch (final Exception ex) {
      MessageUtils.error(ex, 1, "Failed to load files", true);
      return;
    }
    MessageUtils.setPrefix(getSettings().getString("prefix"));

    getLogger().info("Registering commands...");
    Registerer.registerCommand(new MassSayRebornCmd(this));
    Registerer.registerCommand(new MassSayCmd(this));

    getLogger().info("Registering listeners...");
    DeveloperNotifications.enableNotifications("ca19af04-a156-482e-a35d-3f5f434975b5");

    getLogger().info("Loading metrics...");
    new Metrics(this);

    SpigotUpdateChecker.checkForUpdates(63862);

    MessageUtils.console(
        "&aMassSayReborn v" + Common.getVersion() + " by Demeng has been successfully enabled.");
  }

  @Override
  public void onDisable() {
    MessageUtils.console(
        "&cMassSayReborn v" + Common.getVersion() + " by Demeng has been successfully disabled.");
  }

  public FileConfiguration getSettings() {
    return settingsFile.getConfig();
  }

  public CustomConfig getSettingsFile() {
    return settingsFile;
  }
}
