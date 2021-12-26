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
import dev.demeng.pluginbase.TaskUtils;
import dev.demeng.pluginbase.chat.ChatUtils;
import dev.demeng.pluginbase.command.CommandBase;
import dev.demeng.pluginbase.command.annotations.Aliases;
import dev.demeng.pluginbase.command.annotations.Command;
import dev.demeng.pluginbase.command.annotations.Default;
import dev.demeng.pluginbase.command.annotations.Description;
import dev.demeng.pluginbase.command.annotations.Usage;
import lombok.RequiredArgsConstructor;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@RequiredArgsConstructor
@Command("masssay")
@Aliases("massay")
public class MassSayCmd extends CommandBase {

  private static final String COMMAND_PERMISSION = "masssay.use.command";
  private static final String MESSAGE_PERMISSION = "masssay.use.message";

  private final MassSayReborn i;

  @Default
  @Description("Forces all players to say a message or command.")
  @Usage("/masssay </command|message>")
  public void runDefault(CommandSender sender, String[] args) {

    final String message = String.join(" ", args);

    if (message.startsWith("/")) {
      if (!sender.hasPermission(COMMAND_PERMISSION)) {
        ChatUtils.tell(sender, i.getBaseSettings().insufficientPermission()
            .replace("%permission%", COMMAND_PERMISSION));
        return;
      }

    } else if (!sender.hasPermission(MESSAGE_PERMISSION)) {
      ChatUtils.tell(sender, i.getBaseSettings().insufficientPermission()
          .replace("%permission%", MESSAGE_PERMISSION));
      return;
    }

    final long delay = i.getSettings().getLong("delay-between-messages");

    for (final Player p : Bukkit.getServer().getOnlinePlayers()) {
      if (!p.hasPermission("masssay.bypass")) {
        TaskUtils.delay(task -> p.chat(message), delay);
      }
    }
  }
}
