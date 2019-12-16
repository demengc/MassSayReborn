package com.demeng7215.masssayreborn.commands;

import com.demeng7215.demlib.api.CustomCommand;
import com.demeng7215.masssayreborn.MassSayReborn;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Collections;

public class MassSayCmd extends CustomCommand {

	private MassSayReborn i;

	public MassSayCmd(MassSayReborn i) {
		super("masssay");
		setDescription("Forces all online players to say something or execute a command.");
		setAliases(Collections.singletonList("massay"));

		this.i = i;
	}

	@Override
	protected void run(CommandSender sender, String[] args) {

		if (!checkHasPerm("masssay.use", sender, i.getConfiguration().getString("no-perms"))) return;

		if (!checkArgs(args, 1, sender, i.getConfiguration().getString("not-enough-args"))) return;

		String message = String.join(" ", args);

		if (message.startsWith("/")) {
			if (!checkHasPerm("masssay.use.command", sender,
					i.getConfiguration().getString("no-perms"))) return;

		} else if (!checkHasPerm("masssay.use.message", sender,
				i.getConfiguration().getString("no-perms"))) return;

		for (final Player p : Bukkit.getServer().getOnlinePlayers()) {
			Bukkit.getScheduler().scheduleSyncDelayedTask(i, () -> p.chat(message), 10);
		}
	}
}
