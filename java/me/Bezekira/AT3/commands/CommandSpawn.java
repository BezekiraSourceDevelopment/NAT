package main.java.me.cuebyte.nexus.commands;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.annotation.Nullable;

import org.spongepowered.api.command.CommandCallable;
import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;
import org.spongepowered.api.world.Location;
import org.spongepowered.api.world.World;

public class CommandSpawn implements CommandCallable {

	@Override
	public CommandResult process(CommandSource sender, String arguments) throws CommandException {

		String[] args = arguments.split(" ");

		if(args.length > 2) { sender.sendMessage(usage); return CommandResult.success(); }

		if(args[0].equalsIgnoreCase("create")) { new CommandSpawnCreate(sender, args); return CommandResult.success(); }
		else if(args[0].equalsIgnoreCase("remove")) { new CommandSpawnRemove(sender, args); return CommandResult.success(); }
		else if(args[0].equalsIgnoreCase("list")) { new CommandSpawnList(sender, args); return CommandResult.success(); }
		else if(args[0].equalsIgnoreCase("move")) { new CommandSpawnMove(sender, args); return CommandResult.success(); }
		else {
			new CommandSpawnTeleport(sender, args);
		}

		return CommandResult.success();

	}

	private final Text usage = Text.of(TextColors.GOLD, "Usage: ", TextColors.GRAY, "/spawn [create|remove|list|move] [name]");
	private final Text help = Text.of(TextColors.GOLD, "Help: ", TextColors.GRAY, "/spawn [create|remove|list|move] [name]");
	private final Text description = Text.builder("Nexus | Spawn Command").color(TextColors.GOLD).build();
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
