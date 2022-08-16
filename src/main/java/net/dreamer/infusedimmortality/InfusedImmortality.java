package net.dreamer.infusedimmortality;

import net.dreamer.infusedimmortality.item.InfusedimmortalityItemRegistry;
import net.dreamer.infusedimmortality.recipe.InfusedimmortalityRecipeSerializer;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InfusedImmortality implements ModInitializer {
	public static final Logger LOGGER = LoggerFactory.getLogger("Infused Immortality");
	public static String MOD_ID = "infusedimmortality";

	@Override
	public void onInitialize(ModContainer mod) {
		LOGGER.info("Looking for the rum...");



		InfusedimmortalityItemRegistry.register();
		InfusedimmortalityRecipeSerializer.register();
	}
}
