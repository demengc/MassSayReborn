package dev.demeng.msr;

import dev.demeng.demlib.Common;
import dev.demeng.demlib.JoinNotification;
import dev.demeng.demlib.Registerer;
import dev.demeng.demlib.command.CommandMessages;
import dev.demeng.demlib.connection.Metrics;
import dev.demeng.demlib.connection.SpigotUpdateChecker;
import dev.demeng.demlib.core.DemLib;
import dev.demeng.demlib.file.YamlFile;
import dev.demeng.demlib.message.MessageUtils;
import dev.demeng.msr.command.MassSayCmd;
import dev.demeng.msr.command.MassSayRebornCmd;
import lombok.Getter;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.UUID;

public final class MassSayReborn extends JavaPlugin {

  @Getter private YamlFile settingsFile;

  private static final int SETTINGS_VERSION = 2;

  @Override
  public void onEnable() {

    DemLib.setPlugin(this);
    DemLib.setPrefix("&7[&bMassSay&7] &r");

    MessageUtils.console(
        "Enabling MassSayReborn...\n\n"
            + "&b   _____                         _________             \n"
            + "&b  /     \\ _____    ______ ______/   _____/____  ___.__.\n"
            + "&b /  \\ /  \\\\__  \\  /  ___//  ___/\\_____  \\\\__  \\<   |  |\n"
            + "&3/    Y    \\/ __ \\_\\___ \\ \\___ \\ /        \\/ __ \\\\___  |\n"
            + "&3\\____|__  (____  /____  >____  >_______  (____  / ____|\n"
            + "&3        \\/     \\/     \\/     \\/        \\/     \\/\\/     \n");

    getLogger().info("Loading configuration...");

    try {
      settingsFile = new YamlFile("settings.yml");
    } catch (Exception ex) {
      MessageUtils.error(ex, "Failed to load config files.", true);
      return;
    }

    if (getSettings().getInt("config-version", 1) < SETTINGS_VERSION) {
      MessageUtils.error(null, "Outdated settings.yml.", true);
      return;
    }

    MessageUtils.setPrefix(getSettings().getString("prefix"));

    getLogger().info("Registering commands...");
    DemLib.setCommandMessages(new CommandMessages(getSettings()));

    try {
      Registerer.registerCommand(new MassSayRebornCmd(this));
      Registerer.registerCommand(new MassSayCmd(this));

    } catch (IllegalAccessException | NoSuchFieldException ex) {
      MessageUtils.error(ex, "Failed to register commands.", true);
      return;
    }

    getLogger().info("Registering listeners...");
    new JoinNotification(UUID.fromString("ca19af04-a156-482e-a35d-3f5f434975b5"));

    getLogger().info("Loading metrics...");
    new Metrics(this, 4739);

    getLogger().info("Checking for updates...");
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
}
