package dev.demeng.msr.command;

import dev.demeng.demlib.command.CustomCommand;
import dev.demeng.demlib.message.MessageUtils;
import dev.demeng.msr.MassSayReborn;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Collections;

public class MassSayCmd extends CustomCommand {

  private final MassSayReborn i;

  public MassSayCmd(MassSayReborn i) {
    super("masssay", false, null, 1, "</command|message>");

    this.i = i;

    setAliases(Collections.singletonList("massay"));
  }

  @Override
  protected void run(CommandSender sender, String[] args) {

    final String message = String.join(" ", args);

    if (message.startsWith("/")) {
      if (!sender.hasPermission("masssay.use.command")) {
        MessageUtils.tell(sender, i.getSettings().getString("no-permission"));
        return;
      }

    } else if (!sender.hasPermission("masssay.use.message")) {
      MessageUtils.tell(sender, i.getSettings().getString("no-permission"));
      return;
    }

    for (final Player p : Bukkit.getServer().getOnlinePlayers()) {

      if (p.hasPermission("masssay.bypass")) {
        continue;
      }

      Bukkit.getScheduler()
          .scheduleSyncDelayedTask(
              i, () -> p.chat(message), i.getSettings().getLong("delay-between-messages"));
    }
  }
}
