package dev.demeng.msr.command;

import dev.demeng.demlib.Common;
import dev.demeng.demlib.command.CustomCommand;
import dev.demeng.demlib.message.MessageUtils;
import dev.demeng.msr.MassSayReborn;
import org.bukkit.command.CommandSender;

import java.util.Collections;

public class MassSayRebornCmd extends CustomCommand {

  private final MassSayReborn i;

  public MassSayRebornCmd(MassSayReborn i) {
    super("masssayreborn", false, null, 0, "");

    this.i = i;

    setAliases(Collections.singletonList("msr"));
  }

  @Override
  protected void run(CommandSender sender, String[] args) {

    if (args.length == 1
        && args[0].equalsIgnoreCase("reload")
        && sender.hasPermission("masssay.reload")) {

      i.getSettingsFile().reloadConfig();
      MessageUtils.tell(sender, i.getSettings().getString("reloaded"));
      return;
    }

    MessageUtils.tellClean(
        sender,
        "&b&lRunning MassSayReborn v" + Common.getVersion() + " by Demeng.",
        "&bLink: &fhttps://spigotmc.org/resources/63862/",
        "&bType &f/masssay </command|msg> &bto mass say.");
  }
}
