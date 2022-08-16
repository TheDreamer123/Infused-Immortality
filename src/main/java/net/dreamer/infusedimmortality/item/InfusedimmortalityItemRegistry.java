package net.dreamer.infusedimmortality.item;

import net.dreamer.infusedimmortality.InfusedImmortality;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.minecraft.util.registry.Registry;

public class InfusedimmortalityItemRegistry {
    public static final Item INFUSED_TOTEM = new InfusedTotemItem(new Item.Settings().group(ItemGroup.COMBAT).maxCount(1).rarity(Rarity.UNCOMMON));

    public static void register() {
        Registry.register(Registry.ITEM, new Identifier(InfusedImmortality.MOD_ID, "infused_totem"), INFUSED_TOTEM);
    }
}
