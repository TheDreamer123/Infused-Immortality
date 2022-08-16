package net.dreamer.infusedimmortality.item;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionUtil;
import net.minecraft.potion.Potions;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class InfusedTotemItem extends Item {
    private static final Potion defaultPotion = Potions.WATER;

    public InfusedTotemItem(Settings settings) {
        super(settings);
    }

    public void appendStacks(ItemGroup group,DefaultedList<ItemStack> stacks) {
        if(group == this.getGroup()) stacks.add(PotionUtil.setPotion(new ItemStack(this),defaultPotion));
    }

    public ItemStack getDefaultStack() {
        return PotionUtil.setPotion(super.getDefaultStack(),defaultPotion);
    }

    public void appendTooltip(ItemStack stack,@Nullable World world,List<Text> tooltip,TooltipContext context) {
        PotionUtil.buildTooltip(stack, tooltip, 1.0F);
    }

    public boolean hasGlint(ItemStack stack) {
        return super.hasGlint(stack) || !PotionUtil.getPotionEffects(stack).isEmpty();
    }
}
