package net.dreamer.infusedimmortality;

import net.dreamer.infusedimmortality.item.InfusedimmortalityItemRegistry;
import net.fabricmc.fabric.impl.client.rendering.ColorProviderRegistryImpl;
import net.minecraft.potion.PotionUtil;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.client.ClientModInitializer;

public class InfusedImmortalityClient implements ClientModInitializer {

    @Override
    public void onInitializeClient(ModContainer mod) {
        ColorProviderRegistryImpl.ITEM.register((stack,tintIndex) -> PotionUtil.getColor(PotionUtil.getPotion(stack)),InfusedimmortalityItemRegistry.INFUSED_TOTEM);
    }
}
