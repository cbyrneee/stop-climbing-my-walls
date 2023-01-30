package dev.cbyrne.stopclimbingmywalls.mixin;

import dev.cbyrne.stopclimbingmywalls.StopClimbingMyWalls;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.SpiderEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(SpiderEntity.class)
public abstract class SpiderEntityMixin extends HostileEntity {
    /**
     * Required for inheriting HostileEntity.
     */
    protected SpiderEntityMixin(EntityType<? extends HostileEntity> entityType, World world) {
        super(entityType, world);
    }

    /**
     * Only allow spiders to climb if the `canSpidersClimb` game rule is set to `true`
     */
    @Inject(method = "isClimbingWall", at = @At("RETURN"), cancellable = true)
    public void stopClimbingMyWalls$checkGameRule(CallbackInfoReturnable<Boolean> cir) {
        // You may be asking: Why don't we just `cir.setReturnValue(canSpidersClimbWalls)`?

        // The simple answer is: `setReturnValue` cancels the method, we don't want to do that.
        // It's very unlikely that other mods would want to do anything here, but there's no point in altering expected
        // behaviour, especially when in the case of the game rule being `true`, we don't actually need to change anything.
        var canSpidersClimbWalls = StopClimbingMyWalls.getInstance().canSpidersClimbWalls(this.world);
        if (!canSpidersClimbWalls) {
            cir.setReturnValue(false);
        }
    }
}
