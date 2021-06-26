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

package dev.demeng.msr.commands;

import dev.demeng.msr.MassSayReborn;
import dev.demeng.pluginbase.Common;
import dev.demeng.pluginbase.chat.ChatUtils;
import dev.demeng.pluginbase.command.CommandBase;
import dev.demeng.pluginbase.command.annotations.Aliases;
import dev.demeng.pluginbase.command.annotations.Command;
import dev.demeng.pluginbase.command.annotations.Default;
import dev.demeng.pluginbase.command.annotations.Description;
import dev.demeng.pluginbase.command.annotations.Permission;
import dev.demeng.pluginbase.command.annotations.SubCommand;
import dev.demeng.pluginbase.command.annotations.Usage;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.InvalidConfigurationException;

@RequiredArgsConstructor
@Command("masssayreborn")
@Aliases("msr")
public class MassSayRebornCmd extends CommandBase {

  private final MassSayReborn i;

  @Default
  @Description("Displays plugin information.")
  @Usage("/msr")
  public void runDefault(CommandSender sender) {
    ChatUtils.tellColored(
        sender,
        "&3" + ChatUtils.CHAT_LINE,
        "&b&lRunning MassSayReborn v" + Common.getVersion() + " by Demeng.",
        "&bLink: &fhttps://spigotmc.org/resources/63862/",
        "&bType &f/masssay </command|msg> &bto mass say.",
        "&3" + ChatUtils.CHAT_LINE);
  }

  @SubCommand("reload")
  @Description("Reloads configuration files.")
  @Permission("masssay.reload")
  @Usage("/msr reload")
  public void runReload(CommandSender sender) {

    try {
      i.getSettingsFile().reload();
    } catch (InvalidConfigurationException | IOException ex) {
      Common.error(ex, "Failed to reload configuration files.", false);
      return;
    }

    ChatUtils.tell(sender, i.getSettings().getString("reloaded"));
  }
}
