package me.epicgodmc.mccc.data.materials;

import me.epicgodmc.mccc.MCCompletionType;
import me.epicgodmc.mccc.MinecraftVersion;
import me.epicgodmc.mccc.data.Bankable;

public abstract class MaterialBank implements Bankable {
    private final MinecraftVersion version;

    public MaterialBank(MinecraftVersion version) {
        this.version = version;
    }

    @Override
    public MCCompletionType getCompletionType() {
        return MCCompletionType.MATERIAL;
    }

    @Override
    public String getDefaultSeperator() {
        return "_";
    }

    @Override
    public MinecraftVersion getVersion() {
        return this.version;
    }
}
