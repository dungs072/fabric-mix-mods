package com.mode.items.guns;

import com.mode.items.guns.settings.GunSettings;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ArrowItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;

import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Arm;
import net.minecraft.util.Hand;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

// GunItem.java
public class BaseGun extends Item {
    private GunSettings gunSettings;

    public BaseGun(GunSettings settings) {
        super(settings);
        this.gunSettings = settings;
    }
    @Override
    public ActionResult use(World world, PlayerEntity user, Hand hand) {
        if (world.isClient) {
            return ActionResult.PASS;
        }
        ItemStack ammoStack = findAmmo(user);
        if(ammoStack.isEmpty())
        {
            user.sendMessage(Text.literal("Out of ammo"), true);
            return ActionResult.FAIL;
        }
        if(!user.getAbilities().creativeMode) {
            ammoStack.decrement(1);
        }
        ArrowEntity arrow = new ArrowEntity(EntityType.ARROW, world);
        arrow.setDamage(gunSettings.getDamage());
        arrow.setNoGravity(false);
        Vec3d spawnPos = getSpawnPos(user, hand);
        arrow.refreshPositionAndAngles(spawnPos.x, spawnPos.y, spawnPos.z, user.getYaw(), user.getPitch());
        Vec3d look = user.getRotationVec(1.0F);
        arrow.setVelocity(
            look.x,
            look.y,
            look.z,
            gunSettings.getVelocity(),
            gunSettings.getInaccuracy()
        );

        arrow.pickupType = PersistentProjectileEntity.PickupPermission.DISALLOWED;

        // spawn on server
        world.spawnEntity(arrow);
        world.playSound(null, user.getBlockPos(), SoundEvents.ENTITY_GENERIC_EXPLODE.value(), SoundCategory.PLAYERS, 1.0F, 1.0F);

        user.getItemCooldownManager().set(ammoStack, gunSettings.getRate());
        
        return ActionResult.SUCCESS;
    }

    private ItemStack findAmmo(PlayerEntity player) {
        if (isBullet(player.getStackInHand(Hand.OFF_HAND))) return player.getStackInHand(Hand.OFF_HAND);
        if (isBullet(player.getStackInHand(Hand.MAIN_HAND))) return player.getStackInHand(Hand.MAIN_HAND);
        for (int i = 0; i < player.getInventory().size(); i++) {
            ItemStack s = player.getInventory().getStack(i);
            if (isBullet(s)) return s;
        }
        return ItemStack.EMPTY;
    }

    private boolean isBullet(ItemStack stack) {
        return stack.getItem() instanceof ArrowItem;
    }
    private Vec3d getSpawnPos(PlayerEntity player, Hand hand) {
        boolean rightHanded = (player.getMainArm() == Arm.RIGHT);
        Vec3d look = player.getRotationVec(1.0F);

        Vec3d side = look.crossProduct(new Vec3d(0, 1, 0)).normalize();

        double sideOffset = 0.4; 
        if ((rightHanded && hand == Hand.MAIN_HAND) || (!rightHanded && hand == Hand.OFF_HAND)) {
            side = side.multiply( sideOffset);
        } else {
            side = side.multiply(-sideOffset);
        }

        Vec3d spawnPos = player.getPos()
            .add(0, player.getEyeHeight(player.getPose()) - 0.1, 0) 
            .add(side)                                          
            .add(look.multiply(0.5));          
        return spawnPos;
    }
}

