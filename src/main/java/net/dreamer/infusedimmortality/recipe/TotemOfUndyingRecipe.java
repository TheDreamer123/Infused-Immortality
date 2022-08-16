package net.dreamer.infusedimmortality.recipe;

import net.dreamer.infusedimmortality.item.InfusedimmortalityItemRegistry;
import net.minecraft.inventory.CraftingInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.potion.PotionUtil;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.SpecialCraftingRecipe;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.world.World;

public class TotemOfUndyingRecipe extends SpecialCraftingRecipe {
    public TotemOfUndyingRecipe(Identifier identifier) {
        super(identifier);
    }

    public boolean matches(CraftingInventory craftingInventory, World world) {
        boolean bl1 = false;
        boolean bl2 = false;

        for(int i = 0; i < craftingInventory.size(); ++i) {
            ItemStack itemStack = craftingInventory.getStack(i);
            if (!itemStack.isEmpty()) {
                if(itemStack.getItem() == Items.GLASS_BOTTLE && !bl1) bl1 = true;
                else if(itemStack.getItem() == InfusedimmortalityItemRegistry.INFUSED_TOTEM && !bl2) bl2 = true;
                else {
                    if(itemStack != ItemStack.EMPTY) return false;
                }
            }
        }

        return bl1 && bl2;
    }

    public ItemStack craft(CraftingInventory craftingInventory) {
        return new ItemStack(Items.TOTEM_OF_UNDYING);
    }

    @Override
    public boolean isIgnoredInRecipeBook() {
        return false;
    }

    @Override
    public DefaultedList<ItemStack> getRemainder(CraftingInventory inventory) {
        DefaultedList<ItemStack> defaultedList = DefaultedList.ofSize(inventory.size(), ItemStack.EMPTY);

        ItemStack itemStack = ItemStack.EMPTY;

        for(int i = 0; i < inventory.size(); ++i) {
            ItemStack itemStack2 = inventory.getStack(i);
            if (!itemStack2.isEmpty() && itemStack2.getItem() == InfusedimmortalityItemRegistry.INFUSED_TOTEM) {
                itemStack = itemStack2;
                break;
            }
        }

        ItemStack itemStack3 = new ItemStack(Items.POTION, 1);
        PotionUtil.setPotion(itemStack3,PotionUtil.getPotion(itemStack));
        PotionUtil.setCustomPotionEffects(itemStack3,PotionUtil.getCustomPotionEffects(itemStack));

        for(int i = 0; i < defaultedList.size(); ++i) {
            Item item = inventory.getStack(i).getItem();
            if(item == Items.GLASS_BOTTLE) defaultedList.set(i, itemStack3);
        }

        return defaultedList;
    }

    public boolean fits(int width,int height) {
        return width >= 2 && height >= 2;
    }

    public RecipeSerializer<?> getSerializer() {
        return InfusedimmortalityRecipeSerializer.INFUSED_TOTEM;
    }
}
