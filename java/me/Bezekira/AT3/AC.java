package main.java.me.bezekira.AT3;

import java.io.File;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import main.java.me.bezekira.AT3.api.NexusAPI;
import main.java.me.bezekira.AT3.commands.CommandAFK;
import main.java.me.bezekira.AT3.commands.CommandBan;
import main.java.me.bezekira.AT3.commands.CommandBanlist;
import main.java.me.bezekira.AT3.commands.CommandBroadcast;
import main.java.me.bezekira.AT3.commands.CommandButcher;
import main.java.me.bezekira.AT3.commands.CommandChannel;
import main.java.me.bezekira.AT3.commands.CommandEnchant;
import main.java.me.bezekira.AT3.commands.CommandFakejoin;
import main.java.me.bezekira.AT3.commands.CommandFakeleave;
import main.java.me.bezekira.AT3.commands.CommandFeed;
import main.java.me.bezekira.AT3.commands.CommandFly;
import main.java.me.bezekira.AT3.commands.CommandForce;
import main.java.me.bezekira.AT3.commands.CommandGamemode;
import main.java.me.bezekira.AT3.commands.CommandGive;
import main.java.me.bezekira.AT3.commands.CommandGod;
import main.java.me.bezekira.AT3.commands.CommandHeal;
import main.java.me.bezekira.AT3.commands.CommandHome;
import main.java.me.bezekira.AT3.commands.CommandItem;
import main.java.me.bezekira.AT3.commands.CommandJump;
import main.java.me.bezekira.AT3.commands.CommandKick;
import main.java.me.bezekira.AT3.commands.CommandKill;
import main.java.me.bezekira.AT3.commands.CommandList;
import main.java.me.bezekira.AT3.commands.CommandMail;
import main.java.me.bezekira.AT3.commands.CommandMemory;
import main.java.me.bezekira.AT3.commands.CommandMessage;
import main.java.me.bezekira.AT3.commands.CommandMotd;
import main.java.me.bezekira.AT3.commands.CommandMute;
import main.java.me.bezekira.AT3.commands.CommandNexus;
import main.java.me.bezekira.AT3.commands.CommandNick;
import main.java.me.bezekira.AT3.commands.CommandOnlinetime;
import main.java.me.bezekira.AT3.commands.CommandPage;
import main.java.me.bezekira.AT3.commands.CommandPing;
import main.java.me.bezekira.AT3.commands.CommandPowertool;
import main.java.me.bezekira.AT3.commands.CommandRealname;
import main.java.me.bezekira.AT3.commands.CommandReply;
import main.java.me.bezekira.AT3.commands.CommandRules;
import main.java.me.bezekira.AT3.commands.CommandSearchitem;
import main.java.me.bezekira.AT3.commands.CommandSeen;
import main.java.me.bezekira.AT3.commands.CommandSpawn;
import main.java.me.bezekira.AT3.commands.CommandSpeed;
import main.java.me.bezekira.AT3.commands.CommandSpy;
import main.java.me.bezekira.AT3.commands.CommandTP;
import main.java.me.bezekira.AT3.commands.CommandTPA;
import main.java.me.bezekira.AT3.commands.CommandTPAHere;
import main.java.me.bezekira.AT3.commands.CommandTPAccept;
import main.java.me.bezekira.AT3.commands.CommandTPDeath;
import main.java.me.bezekira.AT3.commands.CommandTPDeny;
import main.java.me.bezekira.AT3.commands.CommandTPHere;
import main.java.me.bezekira.AT3.commands.CommandTPPos;
import main.java.me.bezekira.AT3.commands.CommandTPSwap;
import main.java.me.bezekira.AT3.commands.CommandTPWorld;
import main.java.me.bezekira.AT3.commands.CommandTempban;
import main.java.me.bezekira.AT3.commands.CommandTicket;
import main.java.me.bezekira.AT3.commands.CommandTime;
import main.java.me.bezekira.AT3.commands.CommandUnban;
import main.java.me.bezekira.AT3.commands.CommandUnmute;
import main.java.me.bezekira.AT3.commands.CommandVanish;
import main.java.me.bezekira.AT3.commands.CommandWarp;
import main.java.me.bezekira.AT3.commands.CommandWeather;
import main.java.me.bezekira.AT3.commands.CommandWhois;
import main.java.me.bezekira.AT3.customized.NexusDatabase;
import main.java.me.bezekira.AT3.events.EventPlayerChat;
import main.java.me.bezekira.AT3.events.EventPlayerDamage;
import main.java.me.bezekira.AT3.events.EventPlayerDeath;
import main.java.me.bezekira.AT3.events.EventPlayerInteractBlock;
import main.java.me.bezekira.AT3.events.EventPlayerInteractEntity;
import main.java.me.bezekira.AT3.events.EventPlayerJoin;
import main.java.me.bezekira.AT3.events.EventPlayerKick;
import main.java.me.bezekira.AT3.events.EventPlayerLogin;
import main.java.me.bezekira.AT3.events.EventPlayerMove;
import main.java.me.bezekira.AT3.events.EventPlayerQuit;
import main.java.me.bezekira.AT3.events.EventPlayerRespawn;
import main.java.me.bezekira.AT3.events.EventSignChange;
import main.java.me.bezekira.AT3.files.FileChat;
import main.java.me.bezekira.AT3.files.FileCommands;
import main.java.me.bezekira.AT3.files.FileConfig;
import main.java.me.bezekira.AT3.files.FileMessages;
import main.java.me.bezekira.AT3.files.FileMotd;
import main.java.me.bezekira.AT3.files.FileRules;
import main.java.me.bezekira.AT3.utils.EconomyUtils;
import main.java.me.bezekira.AT3.utils.ServerUtils;

import org.spongepowered.api.Game;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.game.state.GameStartingServerEvent;
import org.spongepowered.api.event.game.state.GameStoppingServerEvent;
import org.spongepowered.api.plugin.Plugin;

import com.google.inject.Inject;

@Plugin(id = "nexus", name = "Nexus", version = "1.8.4a")

public class Nexus {

	@Inject
	private Game game;

	@Inject
	Logger logger;

	public static Nexus nexus;

	public static Nexus getInstance() { return nexus; }
	public Game getGame() { return game; }

    @Listener
    public void onEnable(GameStartingServerEvent event) {

    	File folder = new File("config/nexus");
    	if(!folder.exists()) folder.mkdir();

    	Controller.game = game;
    	ServerUtils.sink = game.getServer().getBroadcastChannel();

    	FileConfig.setup();
    	FileChat.setup();
    	FileCommands.setup();
    	FileMessages.setup();
    	FileMotd.setup();
    	FileRules.setup();

    	NexusDatabase.setup(game);
    	NexusDatabase.load(game);

        if (!game.getServiceManager().provide(NexusAPI.class).isPresent()) {
            try {
                game.getServiceManager().setProvider(this, NexusAPI.class, new NexusAPI());
            } catch (Exception e) {
                logger.warning("Error while registering NexusAPI!");
            }
        }

    	game.getEventManager().registerListeners(this, new EventPlayerLogin());
    	game.getEventManager().registerListeners(this, new EventPlayerChat());
    	game.getEventManager().registerListeners(this, new EventPlayerDamage());
    	game.getEventManager().registerListeners(this, new EventPlayerDeath());
    	game.getEventManager().registerListeners(this, new EventPlayerInteractBlock());
    	game.getEventManager().registerListeners(this, new EventPlayerInteractEntity());
    	game.getEventManager().registerListeners(this, new EventPlayerJoin());
    	game.getEventManager().registerListeners(this, new EventPlayerKick());
    	game.getEventManager().registerListeners(this, new EventPlayerMove());
    	game.getEventManager().registerListeners(this, new EventPlayerRespawn());
    	game.getEventManager().registerListeners(this, new EventPlayerQuit());
    	game.getEventManager().registerListeners(this, new EventSignChange());
    	game.getEventManager().registerListeners(this, new EconomyUtils());

    	if(FileCommands.AFK()) game.getCommandManager().register(this, new CommandAFK(), "afk");
    	if(FileCommands.BAN()) game.getCommandManager().register(this, new CommandBan(), "ban");
    	if(FileCommands.BANLIST()) game.getCommandManager().register(this, new CommandBanlist(), "banlist");
    	if(FileCommands.BROADCAST()) game.getCommandManager().register(this, new CommandBroadcast(), "broadcast", "bc", "say");
    	if(FileCommands.BUTCHER()) game.getCommandManager().register(this, new CommandButcher(), "butcher");
    	if(FileCommands.CHANNEL()) game.getCommandManager().register(this, new CommandChannel(), "channel", "ch", "c");
    	if(FileCommands.ENCHANT()) game.getCommandManager().register(this, new CommandEnchant(game), "enchant");
    	if(FileCommands.FAKEJOIN()) game.getCommandManager().register(this, new CommandFakejoin(), "fakejoin");
    	if(FileCommands.FAKELEAVE()) game.getCommandManager().register(this, new CommandFakeleave(), "fakeleave");
    	if(FileCommands.FEED()) game.getCommandManager().register(this, new CommandFeed(), "feed");
    	if(FileCommands.FLY()) game.getCommandManager().register(this, new CommandFly(), "fly");
    	if(FileCommands.FORCE()) game.getCommandManager().register(this, new CommandForce(game), "force", "sudo");
    	if(FileCommands.GAMEMODE()) game.getCommandManager().register(this, new CommandGamemode(), "gamemode", "gm");
    	if(FileCommands.GIVE()) game.getCommandManager().register(this, new CommandGive(), "give", "g");
    	if(FileCommands.GOD()) game.getCommandManager().register(this, new CommandGod(), "god");
    	if(FileCommands.HEAL()) game.getCommandManager().register(this, new CommandHeal(game), "heal");
    	if(FileCommands.HOME()) game.getCommandManager().register(this, new CommandHome(), "home");
    	if(FileCommands.ITEM()) game.getCommandManager().register(this, new CommandItem(), "item", "i");
    	if(FileCommands.JUMP()) game.getCommandManager().register(this, new CommandJump(), "jump", "j");
    	if(FileCommands.KICK()) game.getCommandManager().register(this, new CommandKick(game), "kick");
    	if(FileCommands.KILL()) game.getCommandManager().register(this, new CommandKill(game), "kill");
    	if(FileCommands.LIST()) game.getCommandManager().register(this, new CommandList(game), "list", "who");
    	if(FileCommands.MAIL()) game.getCommandManager().register(this, new CommandMail(), "mail");
    	if(FileCommands.MEMORY()) game.getCommandManager().register(this, new CommandMemory(), "memory");
    	if(FileCommands.MSG()) game.getCommandManager().register(this, new CommandMessage(), "m", "msg", "message", "w", "whisper", "tell");
    	if(FileCommands.MOTD()) game.getCommandManager().register(this, new CommandMotd(), "motd");
    	if(FileCommands.MUTE()) game.getCommandManager().register(this, new CommandMute(game), "mute");
    	if(FileCommands.NEXUS()) game.getCommandManager().register(this, new CommandNexus(), "nexus");
    	if(FileCommands.NICK()) game.getCommandManager().register(this, new CommandNick(), "nick");
    	if(FileCommands.ONLINETIME()) game.getCommandManager().register(this, new CommandOnlinetime(game), "onlinetime");
    	if(FileCommands.PING()) game.getCommandManager().register(this, new CommandPing(), "ping");
    	if(FileCommands.POWERTOOL()) game.getCommandManager().register(this, new CommandPowertool(game), "powertool");
    	if(FileCommands.REALNAME()) game.getCommandManager().register(this, new CommandRealname(), "realname");
    	if(FileCommands.REPLY()) game.getCommandManager().register(this, new CommandReply(), "r", "reply");
    	if(FileCommands.RULES()) game.getCommandManager().register(this, new CommandRules(), "rules");
    	if(FileCommands.SEARCHITEM()) game.getCommandManager().register(this, new CommandSearchitem(), "searchitem", "si", "search");
    	if(FileCommands.SEEN()) game.getCommandManager().register(this, new CommandSeen(game), "seen");
    	if(FileCommands.SPAWN()) game.getCommandManager().register(this, new CommandSpawn(), "spawn");
    	if(FileCommands.SPEED()) game.getCommandManager().register(this, new CommandSpeed(), "speed");
    	if(FileCommands.SPY()) game.getCommandManager().register(this, new CommandSpy(), "spy");
    	if(FileCommands.TEMPBAN()) game.getCommandManager().register(this, new CommandTempban(game), "tempban");
    	if(FileCommands.TICKET()) game.getCommandManager().register(this, new CommandTicket(), "ticket");
    	if(FileCommands.TIME()) game.getCommandManager().register(this, new CommandTime(game), "time");
    	if(FileCommands.TP()) game.getCommandManager().register(this, new CommandTP(game), "tp", "teleport");
    	if(FileCommands.TPA()) game.getCommandManager().register(this, new CommandTPA(game), "tpa");
    	if(FileCommands.TPACCEPT()) game.getCommandManager().register(this, new CommandTPAccept(game), "tpaccept");
    	if(FileCommands.TPAHERE()) game.getCommandManager().register(this, new CommandTPAHere(game), "tpahere");
    	if(FileCommands.TPDEATH()) game.getCommandManager().register(this, new CommandTPDeath(game), "tpdeath", "back");
    	if(FileCommands.TPDENY()) game.getCommandManager().register(this, new CommandTPDeny(game), "tpdeny");
    	if(FileCommands.TPHERE()) game.getCommandManager().register(this, new CommandTPHere(game), "tphere");
    	if(FileCommands.TPPOS()) game.getCommandManager().register(this, new CommandTPPos(game), "tppos");
    	if(FileCommands.TPSWAP()) game.getCommandManager().register(this, new CommandTPSwap(game), "tpswap");
    	if(FileCommands.TPWORLD()) game.getCommandManager().register(this, new CommandTPWorld(game), "tpworld");
    	if(FileCommands.UNBAN()) game.getCommandManager().register(this, new CommandUnban(game), "unban");
    	if(FileCommands.UNMUTE()) game.getCommandManager().register(this, new CommandUnmute(game), "unmute");
    	if(FileCommands.VANISH()) game.getCommandManager().register(this, new CommandVanish(), "vanish", "v");
    	if(FileCommands.WARP()) game.getCommandManager().register(this, new CommandWarp(), "warp");
    	if(FileCommands.WEATHER()) game.getCommandManager().register(this, new CommandWeather(game), "weather");
    	if(FileCommands.WHOIS()) game.getCommandManager().register(this, new CommandWhois(), "whois", "check");

    	game.getCommandManager().register(this, new CommandPage(), "page");

    	game.getScheduler().createTaskBuilder().interval(200, TimeUnit.MILLISECONDS).execute(new Runnable() {
    		@Override
			public void run() {
    			NexusDatabase.commit();
    		}
    	}).submit(this);

    	game.getScheduler().createTaskBuilder().interval(1, TimeUnit.SECONDS).execute(new Runnable() {
    		@Override
			public void run() {
    			ServerUtils.heartbeat();
    		}
    	}).submit(this);

    }

    @Listener
    public void onDisable(GameStoppingServerEvent event) {
    	NexusDatabase.commit();
    }

}
