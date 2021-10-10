package megaminds.jsonchat;

import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.command.argument.TextArgumentType;
import net.minecraft.entity.Entity;
import net.minecraft.network.MessageType;
import net.minecraft.server.command.CommandManager;
import static net.minecraft.server.command.CommandManager.literal;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;
import net.minecraft.text.Texts;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Util;

public class SayRawCommand {
	public static void register(CommandDispatcher<ServerCommandSource> dispatcher, boolean isDedicated) {
		dispatcher.register((literal("sayraw").requires((source)->{
			return source.hasPermissionLevel(2);
		})).then(CommandManager.argument("message", TextArgumentType.text()).executes((context) -> {
			Entity entity = ((ServerCommandSource)context.getSource()).getEntity();
			Text text = Texts.parse((ServerCommandSource)context.getSource(), (Text)TextArgumentType.getTextArgument(context, "message"), entity, 0);
			Text text2 = new TranslatableText("chat.type.announcement", new Object[]{((ServerCommandSource)context.getSource()).getDisplayName(), text});
			if (entity != null) {
				((ServerCommandSource)context.getSource()).getServer().getPlayerManager().broadcastChatMessage(text2, MessageType.CHAT, entity.getUuid());
			} else {
				((ServerCommandSource)context.getSource()).getServer().getPlayerManager().broadcastChatMessage(text2, MessageType.SYSTEM, Util.NIL_UUID);
			}
			return 1;
		})));
	}
}