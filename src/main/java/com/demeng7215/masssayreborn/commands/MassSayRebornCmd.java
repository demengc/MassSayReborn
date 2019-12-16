package com.demeng7215.masssayreborn.commands;

import com.demeng7215.demlib.api.Common;
import com.demeng7215.demlib.api.CustomCommand;
import com.demeng7215.demlib.api.messages.MessageUtils;
import org.bukkit.command.CommandSender;

import java.util.Collections;

public class MassSayRebornCmd extends CustomCommand {

	public MassSayRebornCmd() {
		super("masssayreborn");
		setAliases(Collections.singletonList("msr"));
		setDescription("Displays plugin information.");
	}

	@Override
	protected void run(CommandSender sender, String[] args) {
		MessageUtils.tellWithoutPrefix(sender, "&bRunning CommandButtons v" + Common.getVersion() +
						" by Demeng7215.",
				"&7https://spigotmc.org/resources/63862/",
				"&fType &7/masssay <message>&fto mass say something.");
	}
}
