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

public class InfusedTotemRecipe extends SpecialCraftingRecipe {
    public InfusedTotemRecipe(Identifier identifier) {
        super(identifier);
    }

    public boolean matches(CraftingInventory craftingInventory, World world) {
        boolean bl1 = false;
        boolean bl2 = false;

        for(int i = 0; i < craftingInventory.size(); ++i) {
            ItemStack itemStack = craftingInventory.getStack(i);
            if (!itemStack.isEmpty()) {
                if(itemStack.getItem() == Items.POTION && !bl1) bl1 = true;
                else if(itemStack.getItem() == Items.TOTEM_OF_UNDYING && !bl2) bl2 = true;
                else {
                    if(itemStack != ItemStack.EMPTY) return false;
                }
            }
        }

        return bl1 && bl2;
    }

    public ItemStack craft(CraftingInventory craftingInventory) {
        ItemStack itemStack = ItemStack.EMPTY;

        for(int i = 0; i < craftingInventory.size(); ++i) {
            ItemStack itemStack2 = craftingInventory.getStack(i);
            if (!itemStack2.isEmpty() && itemStack2.getItem() == Items.POTION) {
                itemStack = itemStack2;
                break;
            }
        }

        ItemStack itemStack3 = new ItemStack(InfusedimmortalityItemRegistry.INFUSED_TOTEM, 1);
        PotionUtil.setPotion(itemStack3,PotionUtil.getPotion(itemStack));
        PotionUtil.setCustomPotionEffects(itemStack3,PotionUtil.getCustomPotionEffects(itemStack));

        return itemStack3;
    }

    @Override
    public boolean isIgnoredInRecipeBook() {
        return false;
    }

    @Override
    public DefaultedList<ItemStack> getRemainder(CraftingInventory inventory) {
        DefaultedList<ItemStack> defaultedList = DefaultedList.ofSize(inventory.size(), ItemStack.EMPTY);

        for(int i = 0; i < defaultedList.size(); ++i) {
            Item item = inventory.getStack(i).getItem();
            if(item == Items.POTION) defaultedList.set(i, new ItemStack(Items.GLASS_BOTTLE));
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
