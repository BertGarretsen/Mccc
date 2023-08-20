package me.epicgodmc.mccc;

public enum MCCompletionType
{

    MATERIAL("Material")

    ;
    final String localized;

    MCCompletionType(String localized) {
        this.localized = localized;
    }

    public String getLocalized() {
        return localized;
    }
}
