package me.epicgodmc.mccc.data;


import me.epicgodmc.mccc.MCCompletionType;
import me.epicgodmc.mccc.MinecraftVersion;

public interface Bankable {

    MCCompletionType getCompletionType();

    String getDefaultSeperator();

    MinecraftVersion getVersion();


    String[] buildKeys();
}
