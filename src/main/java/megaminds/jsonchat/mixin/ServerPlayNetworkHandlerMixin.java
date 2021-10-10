package megaminds.jsonchat.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;

@Mixin(value = ServerPlayNetworkHandler.class)
public class ServerPlayNetworkHandlerMixin {
	@ModifyVariable(at = @At(value = "FIELD", target = "Lnet/minecraft/server/network/ServerPlayNetworkHandler;server:Lnet/minecraft/server/MinecraftServer;", ordinal = 0), method = "Lnet/minecraft/server/network/ServerPlayNetworkHandler;handleMessage(Lnet/minecraft/server/filter/TextStream$Message;)V", ordinal = 0)
	private Text beforeSendFilteredMessage(Text text) {
		if (text!=null) {
			String s = (String) ((TranslatableText)text).getArgs()[1];
			if (s.startsWith("{")) {
				return Text.Serializer.fromJson(s);
			} else if (s.startsWith("\\{") || s.startsWith("\\\\")) {
				((TranslatableText)text).getArgs()[1] = s.substring(1);
			}
		}
		return text;
	}

	@ModifyVariable(at = @At(value = "FIELD", target = "Lnet/minecraft/server/network/ServerPlayNetworkHandler;server:Lnet/minecraft/server/MinecraftServer;", ordinal = 0), method = "Lnet/minecraft/server/network/ServerPlayNetworkHandler;handleMessage(Lnet/minecraft/server/filter/TextStream$Message;)V", ordinal = 1)
	private Text beforeSendUnfilteredMessage(Text text) {
		String s = (String) ((TranslatableText)text).getArgs()[1];
		if (s.startsWith("{")) {
			return Text.Serializer.fromJson(s);
		} else if (s.startsWith("\\{") || s.startsWith("\\\\")) {
			((TranslatableText)text).getArgs()[1] = s.substring(1);
		}
		return text;
	}
}