package megaminds.jsonchat;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;

public class JsonChat implements ModInitializer {
	public static Logger LOGGER = LogManager.getLogger(JsonChat.class);

	public static final String MOD_ID = "jsonchat";
	public static final String MOD_NAME = "Json Chat Mod";

	@Override
	public void onInitialize() {
		CommandRegistrationCallback.EVENT.register((dispatcher, commandRegistryAccess, registrationEnvironment) -> SayRawCommand.register(dispatcher));
		LOGGER.info(MOD_NAME+" initialized.");
	}
}