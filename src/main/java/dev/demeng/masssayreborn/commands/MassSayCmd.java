package dev.demeng.masssayreborn.commands;

import dev.demeng.demlib.api.commands.CommandSettings;
import dev.demeng.demlib.api.commands.CommandUtils;
import dev.demeng.demlib.api.commands.types.BaseCommand;
import dev.demeng.demlib.api.messages.MessageUtils;
import dev.demeng.masssayreborn.MassSayReborn;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Collections;
import java.util.List;

public class MassSayCmd implements BaseCommand {

  private final MassSayReborn i;

  public MassSayCmd(MassSayReborn i) {
    this.i = i;
  }

  @Override
  public CommandSettings getSettings() {
    return i.getCommandSettings();
  }

  @Override
  public String getName() {
    return "masssay";
  }

  @Override
  public List<String> getAliases() {
    return Collections.singletonList("massay");
  }

  @Override
  public boolean isPlayerCommand() {
    return false;
  }

  @Override
  public String getPermission() {
    return "masssay.use";
  }

  @Override
  public String getUsage() {
    return "<message|/command>";
  }

  @Override
  public int getArgs() {
    return 1;
  }

  @Override
  public void execute(CommandSender sender, String[] args) {
    final String message = String.join(" ", args);

    if (message.startsWith("/")) {
      if (!CommandUtils.hasPermission(sender, "masssay.use.command")) {
        MessageUtils.tell(sender, i.getSettings().getString("no-perms"));
        return;
      }

    } else if (!CommandUtils.hasPermission(sender, "masssay.use.message")) {
      MessageUtils.tell(sender, i.getSettings().getString("no-perms"));
      return;
    }

    for (final Player p : Bukkit.getServer().getOnlinePlayers()) {
      Bukkit.getScheduler()
          .scheduleSyncDelayedTask(
              i, () -> p.chat(message), i.getSettings().getLong("delay-between-messages"));
    }
  }
}
