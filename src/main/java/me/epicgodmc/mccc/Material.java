package me.epicgodmc.mccc;

import java.util.ArrayList;
import java.util.List;

public class Material
{

    final String name;
    final List<MinecraftVersion> versions;

    public Material(String name, MinecraftVersion version) {
        this.name = name;
        this.versions = new ArrayList<>();

        this.versions.add(version);
    }

    public String getName() {
        return name;
    }

    public List<MinecraftVersion> getVersions() {
        return versions;
    }
}
