package net.dreamer.infusedimmortality.mixin;

import net.dreamer.infusedimmortality.item.InfusedimmortalityItemRegistry;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(ClientPlayNetworkHandler.class)
public class ClientPlayNetworkHandlerMixin {
    @Inject(at = @At(value = "HEAD", target = "Lnet/minecraft/entity/player/PlayerEntity;getStackInHand(Lnet/minecraft/util/Hand;)Lnet/minecraft/item/ItemStack;"), locals = LocalCapture.CAPTURE_FAILEXCEPTION, method = "getActiveTotemOfUndying", cancellable = true)
    private static void getActiveTotemOfUndyingInject(PlayerEntity player,CallbackInfoReturnable<ItemStack> cir) {
        Hand[] var1 = Hand.values();

        for (Hand hand : var1) {
            ItemStack itemStack = player.getStackInHand(hand);
            if (itemStack.isOf(InfusedimmortalityItemRegistry.INFUSED_TOTEM)) cir.setReturnValue(itemStack);
        }
    }
}
