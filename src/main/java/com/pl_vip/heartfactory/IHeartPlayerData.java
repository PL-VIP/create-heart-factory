package com.pl_vip.heartfactory;

import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.util.INBTSerializable;

public interface IHeartPlayerData {
    int getHeartContainers();

    void setHeartContainers(int value);

    int getAnarchicHearts();

    void setAnarchicHearts(int value);

    int getHeartSource();

    void setHeartSource(int value);
}

class HeartPlayerData implements IHeartPlayerData, INBTSerializable<CompoundTag> {
    private int heartContainers;
    private int anarchicHearts;
    private int heartSource;

    @Override
    public int getHeartContainers() {
        return heartContainers;
    }

    @Override
    public void setHeartContainers(int value) {
        heartContainers = value;
    }

    @Override
    public int getAnarchicHearts() {
        return anarchicHearts;
    }

    @Override
    public void setAnarchicHearts(int value) {
        anarchicHearts = value;
    }

    @Override
    public int getHeartSource() {
        return heartSource;
    }

    @Override
    public void setHeartSource(int value) {
        heartSource = value;
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag tag = new CompoundTag();
        tag.putInt("heartContainers", heartContainers);
        tag.putInt("anarchicHearts", anarchicHearts);
        tag.putInt("heartSource", heartSource);
        return tag;
    }

    @Override
    public void deserializeNBT(CompoundTag tag) {
        heartContainers = tag.getInt("heartContainers");
        anarchicHearts = tag.getInt("anarchicHearts");
        heartSource = tag.getInt("heartSource");
    }
}
