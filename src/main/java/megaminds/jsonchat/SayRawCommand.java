package megaminds.jsonchat;

import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.command.argument.TextArgumentType;
import net.minecraft.entity.Entity;
import net.minecraft.server.PlayerManager;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;
import net.minecraft.text.Texts;

import static net.minecraft.server.command.CommandManager.literal;

public class SayRawCommand {
	public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
		dispatcher.register((literal("sayraw").requires((source)-> source.hasPermissionLevel(2))).then(CommandManager.argument("message", TextArgumentType.text()).executes((context) -> {
			Entity entity = context.getSource().getEntity();
			Text text = Texts.parse(context.getSource(), TextArgumentType.getTextArgument(context, "message"), entity, 0);
			Text text2 = Text.translatable("chat.type.announcement", context.getSource().getDisplayName(), text);
			ServerCommandSource serverCommandSource = context.getSource();
			PlayerManager playerManager = serverCommandSource.getServer().getPlayerManager();
			playerManager.broadcast(text2, false);
			return 1;
		})));
	}
}