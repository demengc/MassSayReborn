package dev.demeng.masssayreborn.commands;

import dev.demeng.demlib.api.Common;
import dev.demeng.demlib.api.commands.CommandSettings;
import dev.demeng.demlib.api.commands.types.BaseCommand;
import dev.demeng.demlib.api.messages.MessageUtils;
import dev.demeng.masssayreborn.MassSayReborn;
import org.bukkit.command.CommandSender;

import java.util.Collections;
import java.util.List;

public class MassSayRebornCmd implements BaseCommand {

  private final MassSayReborn i;

  public MassSayRebornCmd(MassSayReborn i) {
    this.i = i;
  }

  @Override
  public CommandSettings getSettings() {
    return i.getCommandSettings();
  }

  @Override
  public String getName() {
    return "masssayreborn";
  }

  @Override
  public List<String> getAliases() {
    return Collections.singletonList("msr");
  }

  @Override
  public boolean isPlayerCommand() {
    return false;
  }

  @Override
  public String getPermission() {
    return null;
  }

  @Override
  public String getUsage() {
    return "";
  }

  @Override
  public int getArgs() {
    return 0;
  }

  @Override
  public void execute(CommandSender sender, String[] args) {
    MessageUtils.tellWithoutPrefix(
        sender,
        "&b&lRunning MassSayReborn v" + Common.getVersion() + " by Demeng.",
        "&&bLink: &fhttps://spigotmc.org/resources/63862/",
        "&bType &f/masssay </cmd|msg> &bto mass say a command/message.");
  }
}
