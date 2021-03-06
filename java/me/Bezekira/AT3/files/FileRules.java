package main.java.me.cuebyte.nexus.files;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import ninja.leaping.configurate.ConfigurationNode;
import ninja.leaping.configurate.ConfigurationOptions;
import ninja.leaping.configurate.hocon.HoconConfigurationLoader;
import ninja.leaping.configurate.loader.ConfigurationLoader;

public class FileRules {
	
	public static File file = new File("config/nexus/rules.conf");
	public static ConfigurationLoader<?> manager = HoconConfigurationLoader.builder().setFile(file).build();
	public static ConfigurationNode rules = manager.createEmptyNode(ConfigurationOptions.defaults());

	public static void setup() {

		try {
			
			if (!file.exists()) {
				
				file.createNewFile();
				
				List<String> message = new ArrayList<String>();
				message.add("&c- Respect others.");
				message.add("&c- Griefing / theft will be punished.");
				message.add("&c- Don't flame or troll.");
				
				rules.getNode("message").setValue(message);
				rules.getNode("version").setValue(1);
				
		        manager.save(rules);
				
			}
			
			rules = manager.load();
			
		} catch (IOException e) { e.printStackTrace(); }
		
	}
	
	public static List<String> MESSAGE() { return rules.getNode("message").getList(transform); }
	
	public static Function<Object,String> transform = new Function<Object,String>() {
	    @Override
	    public String apply(Object input) {
	        if (input instanceof String) return (String) input;
	        else return null;
	    }
	};
	
}
