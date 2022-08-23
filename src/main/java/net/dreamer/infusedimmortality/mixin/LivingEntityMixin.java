package net.dreamer.infusedimmortality.mixin;

import net.dreamer.infusedimmortality.item.InfusedimmortalityItemRegistry;
import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.potion.PotionUtil;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity {
    @Shadow public abstract ItemStack getStackInHand(Hand hand);
    @Shadow public abstract void setHealth(float health);
    @Shadow public abstract boolean clearStatusEffects();
    @Shadow public abstract boolean addStatusEffect(StatusEffectInstance effect);

    public LivingEntityMixin(EntityType<?> entityType,World world) {
        super(entityType,world);
    }

    @Inject(at = @At(value = "HEAD"), method = "tryUseTotem", cancellable = true)
    public void tryUseTotemInject(DamageSource source,CallbackInfoReturnable<Boolean> cir) {
        if (source.isOutOfWorld()) cir.setReturnValue(false);
        else {
            ItemStack itemStack = null;
            Hand[] var4 = Hand.values();

            for (Hand hand : var4) {
                ItemStack itemStack2 = this.getStackInHand(hand);
                if(getStackInHand(Hand.MAIN_HAND).getItem() != Items.TOTEM_OF_UNDYING)
                    if (itemStack2.isOf(InfusedimmortalityItemRegistry.INFUSED_TOTEM)) {
                        itemStack = itemStack2.copy();
                        itemStack2.decrement(1);
                        break;
                    }
            }

            if (itemStack != null) {
                if ((LivingEntity) (Entity) this instanceof ServerPlayerEntity serverPlayerEntity) {
                    serverPlayerEntity.incrementStat(Stats.USED.getOrCreateStat(InfusedimmortalityItemRegistry.INFUSED_TOTEM));
                    Criteria.USED_TOTEM.trigger(serverPlayerEntity, new ItemStack(Items.TOTEM_OF_UNDYING));
                }

                this.setHealth(1.0F);
                this.clearStatusEffects();
                List<StatusEffectInstance> list = PotionUtil.getPotionEffects(itemStack);

                for (StatusEffectInstance statusEffectInstance : list) {
                    if (statusEffectInstance.getEffectType().isInstant())
                        statusEffectInstance.getEffectType().applyInstantEffect(this,this,(LivingEntity)(Entity) this,statusEffectInstance.getAmplifier(),1.0D);
                    else this.addStatusEffect(new StatusEffectInstance(statusEffectInstance));
                }

                this.world.sendEntityStatus(this, (byte)69420);
                cir.setReturnValue(true);
            }
        }
    }
}
