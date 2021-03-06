package main.java.me.cuebyte.nexus.commands;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.annotation.Nullable;

import main.java.me.cuebyte.nexus.utils.PermissionsUtils;
import main.java.me.cuebyte.nexus.utils.ServerUtils;

import org.spongepowered.api.Game;
import org.spongepowered.api.command.CommandCallable;
import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;
import org.spongepowered.api.world.Location;
import org.spongepowered.api.world.World;

public class CommandTP implements CommandCallable {

	public Game game;

	public CommandTP(Game game) {
		this.game = game;
	}

	@Override
	public CommandResult process(CommandSource sender, String arguments) throws CommandException {

		String[] args = arguments.split(" ");

		if(!PermissionsUtils.has(sender, "nexus.tp")) { sender.sendMessage(Text.builder("You do not have permissions!").color(TextColors.RED).build()); return CommandResult.success(); }

		if(arguments.equalsIgnoreCase("")) { sender.sendMessage(Text.of(TextColors.GOLD, "Usage: ", TextColors.GRAY, "/tp <player> [target]")); return CommandResult.success(); }
		if(args.length < 1 || args.length > 2) { sender.sendMessage(Text.of(TextColors.GOLD, "Usage: ", TextColors.GRAY, "/tp <player> [target]")); return CommandResult.success(); }

		Player player = null;
		Player target = null;

		if(args.length == 1) {

			if(sender instanceof Player == false) { sender.sendMessage(Text.builder("Cannot be run by the console!").color(TextColors.RED).build()); return CommandResult.success(); }

			player = (Player)sender;
			target = ServerUtils.getPlayer(args[0]);

		}
		else if(args.length == 2) {

			if(!PermissionsUtils.has(sender, "nexus.tp-others")) { sender.sendMessage(Text.builder("You do not have permissions to teleport others!").color(TextColors.RED).build()); return CommandResult.success(); }

			player = ServerUtils.getPlayer(args[0]);
			target = ServerUtils.getPlayer(args[1]);

		}

		if(player == null) {
			sender.sendMessage(Text.builder("Player not found!").color(TextColors.RED).build());
			return CommandResult.success();
		}

		if(target == null) {
			sender.sendMessage(Text.builder("Target not found!").color(TextColors.RED).build());
			return CommandResult.success();
		}

		player.setLocation(target.getLocation());

		if(args.length == 1) {

			sender.sendMessage(Text.of(TextColors.GRAY, "Teleported to ", TextColors.GOLD, target.getName()));

		}
		else if(args.length == 2) {

			sender.sendMessage(Text.of(TextColors.GRAY, "Teleported ", TextColors.GOLD, player.getName(), TextColors.GRAY, " to ", TextColors.GOLD, target.getName()));

		}

		return CommandResult.success();

	}

	private final Text usage = Text.builder("Usage: /tp <player> [target]").color(TextColors.GOLD).build();
	private final Text help = Text.builder("Help: /tp <player> [target]").color(TextColors.GOLD).build();
	private final Text description = Text.builder("Nexus | TP Command").color(TextColors.GOLD).build();
	private List<String> suggestions = new ArrayList<String>();
	private String permission = "";

	@Override
	public Text getUsage(CommandSource sender) { return usage; }
	@Override
	public Optional<Text> getHelp(CommandSource sender) { return Optional.of(help); }
	@Override
	public Optional<Text> getShortDescription(CommandSource sender) { return Optional.of(description); }
	@Override
	public List<String> getSuggestions(CommandSource arg0, String arg1,	@Nullable Location<World> arg2) throws CommandException { return suggestions; }
	@Override
	public boolean testPermission(CommandSource sender) { return permission.equals("") ? true : sender.hasPermission(permission); }

}
