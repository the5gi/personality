package com.teamabnormals.personality.core.mixin.client;

import com.teamabnormals.personality.core.Personality;
import com.teamabnormals.personality.core.other.PersonalityEvents;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.AgeableListModel;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(HumanoidModel.class)
public abstract class HumanoidModelMixin<T extends LivingEntity> extends AgeableListModel<T> {

	@Final
	@Shadow
	public ModelPart rightArm;

	@Final
	@Shadow
	public ModelPart leftArm;

	@Final
	@Shadow
	public ModelPart rightLeg;

	@Final
	@Shadow
	public ModelPart leftLeg;

	@Inject(method = "setupAnim(Lnet/minecraft/world/entity/LivingEntity;FFFFF)V", at = @At("HEAD"))
	public void sitModel(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, CallbackInfo ci) {
		if (entityIn instanceof Player player) {
			if (Personality.SYNCED_SITTING_PLAYERS.contains(player.getUUID()))
				this.riding = true;
		}
	}
}
