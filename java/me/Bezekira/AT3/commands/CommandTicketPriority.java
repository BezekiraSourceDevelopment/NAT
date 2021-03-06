package main.java.me.cuebyte.nexus.commands;

import main.java.me.cuebyte.nexus.customized.NexusDatabase;
import main.java.me.cuebyte.nexus.customized.NexusTicket;
import main.java.me.cuebyte.nexus.utils.PermissionsUtils;
import main.java.me.cuebyte.nexus.utils.ServerUtils;

import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;


public class CommandTicketPriority {

	public CommandTicketPriority(CommandSource sender, String[] args) {
		
		if(sender instanceof Player == false) { sender.sendMessage(Text.builder("Cannot be run by the console!").color(TextColors.RED).build()); return; }
		
		if(!PermissionsUtils.has(sender, "nexus.ticket.priority")) { sender.sendMessage(Text.builder("You do not have permissions!").color(TextColors.RED).build()); return; }
		
		if(args.length != 3) { sender.sendMessage(Text.of(TextColors.GOLD, "Usage: ", TextColors.GRAY, "/ticket priority <id> <priority>")); return; }
	
		Player player = (Player) sender;
		
		int id;
		try { id = Integer.parseInt(args[1]); }
		catch(NumberFormatException e) {
			sender.sendMessage(Text.builder("<id> has to be a number!").color(TextColors.RED).build());
			return;
		}
		
		NexusTicket ticket = NexusDatabase.getTicket(id);
		
		if(ticket == null) {
			sender.sendMessage(Text.builder("Ticket with that ID does not exist!").color(TextColors.RED).build());
			return;
		}
		
		String priority = args[2].toLowerCase();
		if(!priority.equalsIgnoreCase("low")
		&& !priority.equalsIgnoreCase("medium")
		&& !priority.equalsIgnoreCase("high")) {
			sender.sendMessage(Text.builder("<priority> has to be: low, medium or high").color(TextColors.RED).build());
			return;
		}
		
		if(!PermissionsUtils.has(sender, "nexus.ticket.priority-others")) {
			if(ticket.getUUID().equalsIgnoreCase(player.getUniqueId().toString())) {
				
			}
			else if(ticket.getAssigned().equalsIgnoreCase(player.getUniqueId().toString()) && PermissionsUtils.has(sender, "nexus.ticket.priority-assigned")) {
				
			}
			else {
				sender.sendMessage(Text.builder("You do not have permissions to change the priority of this ticket!").color(TextColors.RED).build());
				return;
			}
		}
		
		ticket.setPriority(priority);
		ticket.update();
		
		Text p = Text.of(TextColors.DARK_GREEN, "Low");
		if(ticket.getPriority().equalsIgnoreCase("medium")) p = Text.of(TextColors.GOLD, "Medium");
		else if(ticket.getPriority().equalsIgnoreCase("high")) p = Text.of(TextColors.RED, "High");
		
		sender.sendMessage(Text.of(TextColors.GRAY, "Priority of ticket ", TextColors.YELLOW, "#", id, TextColors.GRAY, " has been changed to ", p));
		
		ServerUtils.broadcast("nexus.ticket.notify", Text.of(TextColors.GOLD, sender.getName(), TextColors.GRAY, " has changed the priority of ticket ", TextColors.YELLOW, "#", id, TextColors.GRAY, " to ", p));
		
	}

}
