package dev.demeng.masssayreborn.commands;

import dev.demeng.demlib.api.commands.CommandSettings;
import dev.demeng.demlib.api.commands.types.SubCommand;
import dev.demeng.demlib.api.messages.MessageUtils;
import dev.demeng.masssayreborn.MassSayReborn;
import org.bukkit.command.CommandSender;

import java.util.Collections;
import java.util.List;

public class ReloadCmd implements SubCommand {

  private final MassSayReborn i;

  public ReloadCmd(MassSayReborn i) {
    this.i = i;
  }

  @Override
  public String getBaseCommand() {
    return "masssayreborn";
  }

  @Override
  public CommandSettings getSettings() {
    return i.getCommandSettings();
  }

  @Override
  public String getName() {
    return "reload";
  }

  @Override
  public List<String> getAliases() {
    return Collections.emptyList();
  }

  @Override
  public boolean isPlayerCommand() {
    return false;
  }

  @Override
  public String getPermission() {
    return "masssayreborn.reload";
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
    i.getSettingsFile().reloadConfig();
    MessageUtils.tell(sender, i.getSettings().getString("reloaded"));
  }
}
