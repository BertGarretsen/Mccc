package me.epicgodmc.mccc;

import com.intellij.codeInsight.completion.CompletionContributor;
import com.intellij.codeInsight.completion.CompletionType;
import com.intellij.patterns.PlatformPatterns;

public class McCompletionContributor extends CompletionContributor
{

    public McCompletionContributor() {
        extend(CompletionType.BASIC, PlatformPatterns.psiElement().inFile(PlatformPatterns.psiFile()), new YamlCompletionProvider());
    }
}
