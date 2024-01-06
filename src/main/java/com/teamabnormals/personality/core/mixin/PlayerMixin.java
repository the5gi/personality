package com.teamabnormals.personality.core.mixin;

import com.teamabnormals.personality.core.Personality;
import com.teamabnormals.personality.core.other.PersonalityEvents;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Player.class)
public abstract class PlayerMixin extends LivingEntity {

	public PlayerMixin(EntityType<? extends LivingEntity> type, Level level) {
		super(type, level);
	}

	@Override
	public void move(MoverType type, Vec3 pos) {
		double x = pos.x();
		double y = pos.y();
		double z = pos.z();
		if (Personality.SITTING_PLAYERS.contains(this.getUUID()) && Math.cbrt(x * x + y * y + z * z) >= 0.185) {
			Personality.SITTING_PLAYERS.remove(this.getUUID());
		}
		super.move(type, pos);
	}
}
