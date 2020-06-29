package dev.demeng.masssayreborn;

import dev.demeng.demlib.DemLib;
import dev.demeng.demlib.api.Common;
import dev.demeng.demlib.api.DeveloperNotifications;
import dev.demeng.demlib.api.Registerer;
import dev.demeng.demlib.api.commands.CommandSettings;
import dev.demeng.demlib.api.connections.SpigotUpdateChecker;
import dev.demeng.demlib.api.files.CustomConfig;
import dev.demeng.demlib.api.messages.MessageUtils;
import dev.demeng.masssayreborn.commands.MassSayCmd;
import dev.demeng.masssayreborn.commands.MassSayRebornCmd;
import dev.demeng.masssayreborn.commands.ReloadCmd;
import org.bstats.bukkit.Metrics;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public final class MassSayReborn extends JavaPlugin {

  private CustomConfig settingsFile;

  private CommandSettings commandSettings;

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
    this.commandSettings = new CommandSettings();
    commandSettings.setNotPlayerMessage("");
    commandSettings.setNoPermissionMessage(getSettings().getString("no-perms"));
    commandSettings.setIncorrectUsageMessage(getSettings().getString("not-enough-args"));

    Registerer.registerCommand(new MassSayRebornCmd(this));
    Registerer.registerCommand(new ReloadCmd(this));
    Registerer.registerCommand(new MassSayCmd(this));

    getLogger().info("Registering listeners...");
    DeveloperNotifications.enableNotifications("ca19af04-a156-482e-a35d-3f5f434975b5");

    getLogger().info("Loading metrics...");
    new Metrics(this, 4739);

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

  public CommandSettings getCommandSettings() {
    return commandSettings;
  }
}
