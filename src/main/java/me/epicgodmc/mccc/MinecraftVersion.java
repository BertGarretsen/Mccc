package me.epicgodmc.mccc;

public enum MinecraftVersion
{

    MC_1_8_8("1.8.8"),
    MC_1_12_2("1.12.2"),
    MC_1_13_2("1.13.2"),
    MC_1_20_1("1.20.1"),


    BUFFER("BUFFER"),
    XSeries("XSeries")


    ;

    String localized;

    MinecraftVersion(String localized) {
        this.localized = localized;
    }


    public String getLocalized() {
        return localized;
    }
}
