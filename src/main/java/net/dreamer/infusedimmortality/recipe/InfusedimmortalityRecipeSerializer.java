package net.dreamer.infusedimmortality.recipe;

import net.dreamer.infusedimmortality.InfusedImmortality;
import net.minecraft.recipe.SpecialRecipeSerializer;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public interface InfusedimmortalityRecipeSerializer {
    SpecialRecipeSerializer<InfusedTotemRecipe> INFUSED_TOTEM = new SpecialRecipeSerializer<>(InfusedTotemRecipe::new);
    SpecialRecipeSerializer<TotemOfUndyingRecipe> TOTEM = new SpecialRecipeSerializer<>(TotemOfUndyingRecipe::new);

    static void register() {
        Registry.register(Registry.RECIPE_SERIALIZER, new Identifier(InfusedImmortality.MOD_ID, "crafting_special_infused_totem"),INFUSED_TOTEM);
        Registry.register(Registry.RECIPE_SERIALIZER, new Identifier(InfusedImmortality.MOD_ID, "crafting_special_totem_of_undying"),TOTEM);
    }
}
