package megaminds.jsonchat;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.fabricmc.api.ModInitializer;

public class JsonChat implements ModInitializer {
	public static Logger LOGGER = LogManager.getLogger(JsonChat.class);

	public static final String MOD_ID = "jsonchat";
	public static final String MOD_NAME = "Json Chat Mod";

	@Override
	public void onInitialize() {
		LOGGER.info(MOD_NAME+" initialized.");
	}
}