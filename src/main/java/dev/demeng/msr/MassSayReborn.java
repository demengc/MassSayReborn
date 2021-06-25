/*
 * MIT License
 *
 * Copyright (c) 2021 Demeng Chen
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package dev.demeng.msr;

import dev.demeng.msr.commands.MassSayCmd;
import dev.demeng.msr.commands.MassSayRebornCmd;
import dev.demeng.pluginbase.BaseSettings;
import dev.demeng.pluginbase.Common;
import dev.demeng.pluginbase.UpdateChecker;
import dev.demeng.pluginbase.YamlConfig;
import dev.demeng.pluginbase.chat.ChatUtils;
import dev.demeng.pluginbase.plugin.BasePlugin;
import dev.demeng.pluginbase.utils.TaskUtils;
import java.io.IOException;
import lombok.Getter;
import org.bstats.bukkit.Metrics;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;

public final class MassSayReborn extends BasePlugin {

  @Getter YamlConfig settingsFile;
  private static final int SETTINGS_VERSION = 3;

  @Override
  public void enable() {

    ChatUtils.coloredConsole("\n\n"
        + "&b   _____                         _________             \n"
        + "&b  /     \\ _____    ______ ______/   _____/____  ___.__.\n"
        + "&b /  \\ /  \\\\__  \\  /  ___//  ___/\\_____  \\\\__  \\<   |  |\n"
        + "&3/    Y    \\/ __ \\_\\___ \\ \\___ \\ /        \\/ __ \\\\___  |\n"
        + "&3\\____|__  (____  /____  >____  >_______  (____  / ____|\n"
        + "&3        \\/     \\/     \\/     \\/        \\/     \\/\\/     \n\n");

    getLogger().info("Loading configuration files...");

    try {
      settingsFile = new YamlConfig("settings.yml");
    } catch (IOException | InvalidConfigurationException ex) {
      Common.error(ex, "Failed to load configuration files.", true);
      return;
    }

    if (!settingsFile.configUpToDate(SETTINGS_VERSION)) {
      Common.error(null, "Configuration files are outdated.", true);
      return;
    }

    getLogger().info("Registering commands...");
    getCommandManager().register(new MassSayRebornCmd(this));
    getCommandManager().register(new MassSayCmd(this));

    getLogger().info("Loading metrics...");
    new Metrics(this, 4739);

    getLogger().info("Checking for updates...");
    checkUpdates();

    ChatUtils.console("&aMassSayReborn v" + Common.getVersion() +
        " by Demeng has been successfully enabled!");
  }

  @Override
  public void disable() {
    ChatUtils.console("&cMassSayReborn v" + Common.getVersion() +
        " by Demeng has been successfully disabled.");
  }

  @Override
  public BaseSettings getBaseSettings() {
    return new BaseSettings() {

      @Override
      public String prefix() {
        return getSettings().getString("prefix");
      }

      @Override
      public String insufficientPermission() {
        return getSettings().getString("insufficient-permission");
      }

      @Override
      public String incorrectUsage() {
        return getSettings().getString("incorrect-usage");
      }
    };
  }

  public FileConfiguration getSettings() {
    return settingsFile.getConfig();
  }

  private void checkUpdates() {

    TaskUtils.runAsync(task -> {
      final UpdateChecker checker = new UpdateChecker(63862);

      switch (checker.getResult()) {
        case OUTDATED:
          ChatUtils.coloredConsole(
              "&2" + ChatUtils.CONSOLE_LINE,
              "&aA newer version of " + Common.getName() + " is available!",
              "&aCurrent version: &r" + Common.getVersion(),
              "&aLatest version: &r" + checker.getLatestVersion(),
              "&aGet the update: &rhttps://spigotmc.org/resources/" + checker.getResourceId(),
              "&2" + ChatUtils.CONSOLE_LINE);
          break;

        case ERROR:
          getLogger().warning("Failed to check for updates.");
          break;

        default:
          break;
      }
    });
  }
}
