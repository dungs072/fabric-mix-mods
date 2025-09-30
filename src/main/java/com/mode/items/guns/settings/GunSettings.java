package com.mode.items.guns.settings;

import net.minecraft.item.Item;

public class GunSettings extends Item.Settings{

    private int rate = 10; 
    private float velocity = 3.0F;  
    private float inaccuracy = 1.0F;
    private float damage = 4.0F;
    private float bulletsPerShot = 1; 
    private float reloadTime = 5F;
    private int magazineSize = 6;
    private float recoil = 1.0F;

    public GunSettings() {
        super();
    }

    public int getRate() {
        return rate;
    }

    public float getVelocity() {
        return velocity;
    }

    public float getInaccuracy() {
        return inaccuracy;
    }

    public float getDamage() {
        return damage;
    }

    public float getBulletsPerShot() {
        return bulletsPerShot;
    }

    public float getReloadTime() {
        return reloadTime;
    }

    public int getMagazineSize() {
        return magazineSize;
    }

    public float getRecoil() {
        return recoil;
    }

    public GunSettings setRate(int rate) {
        this.rate = rate;
        return this;
    }
    public GunSettings setVelocity(float velocity) {
        this.velocity = velocity;
        return this;
    }
    public GunSettings setInaccuracy(float inaccuracy) {
        this.inaccuracy = inaccuracy;
        return this;
    }
    public GunSettings setDamage(float damage) {
        this.damage = damage;
        return this;
    }
    public GunSettings setBulletsPerShot(float bulletsPerShot) {
        this.bulletsPerShot = bulletsPerShot;
        return this;
    }
    public GunSettings setReloadTime(float reloadTime) {
        this.reloadTime = reloadTime;
        return this;
    }
    public GunSettings setMagazineSize(int magazineSize) {
        this.magazineSize = magazineSize;
        return this;
    }
    public GunSettings setRecoil(float recoil) {
        this.recoil = recoil;
        return this;
    }

}
