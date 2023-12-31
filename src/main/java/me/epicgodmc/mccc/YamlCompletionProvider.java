package me.epicgodmc.mccc;

import com.intellij.codeInsight.completion.CompletionParameters;
import com.intellij.codeInsight.completion.CompletionProvider;
import com.intellij.codeInsight.completion.CompletionResultSet;
import com.intellij.util.ProcessingContext;
import me.epicgodmc.mccc.data.CompletionBank;
import org.jetbrains.annotations.NotNull;

public class YamlCompletionProvider extends CompletionProvider<CompletionParameters>
{


    @Override
    protected void addCompletions(@NotNull CompletionParameters parameters, @NotNull ProcessingContext context, @NotNull CompletionResultSet result) {
        result.addAllElements(CompletionBank.getInstance().getCompletions(MCCompletionType.MATERIAL));
    }
}
