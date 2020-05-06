package com.demeng7215.masssayreborn.commands;

import com.demeng7215.demlib.api.Common;
import com.demeng7215.demlib.api.CustomCommand;
import com.demeng7215.demlib.api.messages.MessageUtils;
import com.demeng7215.masssayreborn.MassSayReborn;
import org.bukkit.command.CommandSender;

import java.util.Collections;

public class MassSayRebornCmd extends CustomCommand {

  private final MassSayReborn i;

  public MassSayRebornCmd(MassSayReborn i) {
    super("masssayreborn");

    this.i = i;

    setAliases(Collections.singletonList("msr"));
    setDescription("Displays plugin information.");
  }

  @Override
  protected void run(CommandSender sender, String[] args) {

    if (args.length >= 1 && args[0].equalsIgnoreCase("reload")) {
      i.getSettingsFile().reloadConfig();
      MessageUtils.tell(sender, i.getSettings().getString("reloaded"));
      return;
    }

    MessageUtils.tellWithoutPrefix(
        sender,
        "&b&lRunning MassSayReborn v" + Common.getVersion() + " by Demeng.",
        "&&bLink: &fhttps://spigotmc.org/resources/63862/",
        "&bType &f/masssay </cmd|msg> &bto mass say a command/message.");
  }
}
