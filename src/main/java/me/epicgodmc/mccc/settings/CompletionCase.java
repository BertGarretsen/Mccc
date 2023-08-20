package me.epicgodmc.mccc.settings;

import java.util.function.Function;

public enum CompletionCase
{

    UPPERCASE((in) -> new String[] {in.toUpperCase()}),
    LOWERCASE((in) -> new String[] {in.toLowerCase()}),
    BOTH((in) -> new String[] {in.toLowerCase(), in.toUpperCase()})

    ;
    private Function<String, String[]> transformer;

    CompletionCase(Function<String, String[]> stringTransformer) {
        this.transformer = stringTransformer;
    }


    public String[] transform(String input) {
        return this.transformer.apply(input);
    }
}
