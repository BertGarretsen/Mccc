package me.epicgodmc.mccc.data;

import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.startup.StartupActivity;
import me.epicgodmc.mccc.Icons;
import me.epicgodmc.mccc.MCCompletionType;
import me.epicgodmc.mccc.MinecraftVersion;
import me.epicgodmc.mccc.data.materials.*;
import me.epicgodmc.mccc.settings.PluginSettingsState;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public final class CompletionBank implements StartupActivity {


    private static final Map<MCCompletionType, Map<String, String>> bankNew = new HashMap<>();
    private static CompletionBank instance;

    public static CompletionBank getInstance() {
        return instance;
    }

    public List<LookupElementBuilder> getCompletions(MCCompletionType completionType) {
        List<LookupElementBuilder> result = new ArrayList<>();

        bankNew.get(completionType).forEach((k, v) -> {
            result.add(LookupElementBuilder.create(k).withIcon(Icons.BLOCK_ICON).withTypeText(v).withTailText(" "+completionType.getLocalized()));
        });

        return result;
    }

    public String formatVersions(List<MinecraftVersion> versions) {
        MinecraftVersion[] mcVersions = MinecraftVersion.values();
        versions.sort(Comparator.comparingInt(Enum::ordinal));

        if (versions.size() > 1) {
            StringBuilder builder = new StringBuilder("( ");
            int n = versions.size();

            int start = versions.get(0).ordinal();
            int end = versions.get(0).ordinal();

            for (int i = 1; i < n; i++) {
                if (versions.get(i).ordinal() == end + 1) {
                    end = versions.get(i).ordinal();
                } else {
                    if (start == end) {
                        builder.append(mcVersions[start].getLocalized()).append(", ");
                    } else {
                        builder.append(mcVersions[start].getLocalized()).append("-").append(mcVersions[end].getLocalized()).append(",");
                    }
                    start = end = versions.get(i).ordinal();
                }
            }

            if (start == end) {
                builder.append(mcVersions[start].getLocalized());
            } else {
                builder.append(mcVersions[start].getLocalized()).append("-").append(mcVersions[end].getLocalized());
            }
            builder.append(" )");
            return builder.toString();
        }
        return "( " + versions.get(0).getLocalized() + " )";
    }


    private void initBankable(Bankable bankable, Map<MCCompletionType, Map<String, List<MinecraftVersion>>> to) {
        MinecraftVersion ver = bankable.getVersion();

        if (!to.containsKey(bankable.getCompletionType())) to.put(bankable.getCompletionType(), new HashMap<>());

        Map<String, List<MinecraftVersion>> bank = to.get(bankable.getCompletionType());
        for (String s : bankable.buildKeys()) {
            String[] finalizedKeys = finalizeKey(s, bankable.getDefaultSeperator());

            for (String finalKey : finalizedKeys) {
                if (!bank.containsKey(finalKey)) {
                    List<MinecraftVersion> verlist = new ArrayList<>();
                    verlist.add(ver);
                    bank.put(finalKey, verlist);
                } else {
                    bank.get(finalKey).add(ver);
                }
            }


        }
    }

    private String[] finalizeKey(String in, String defaultSeperator) {
        PluginSettingsState settings = PluginSettingsState.getInstance();

        String[] transformed = settings.completionCase.transform(in);

        if (!settings.completionSeperator.equals(defaultSeperator)) {
            for (int i = 0; i < transformed.length; i++) {
                transformed[i] = transformed[i].replace(defaultSeperator, settings.completionSeperator);
            }
        }
        return transformed;
    }

    public void initDefaultBank() {
        if (!bankNew.isEmpty()) bankNew.clear();

        Map<MCCompletionType, Map<String, List<MinecraftVersion>>> temp_bank = new HashMap<>();

        initBankable(new Materials_1_8_8(), temp_bank);
        initBankable(new Materials_1_12_2(), temp_bank);
        initBankable(new Materials_1_13_2(), temp_bank);
        initBankable(new Materials_1_20_1(), temp_bank);
        initBankable(new Materials_XSeries(), temp_bank);

        for (MCCompletionType type : temp_bank.keySet()) {
            HashMap<String, String> bank = new HashMap<>();

            temp_bank.get(type).forEach((k, v) -> {
                bank.put(k, formatVersions(v));
            });

            bankNew.put(type, bank);

        }
    }

    @Override
    public void runActivity(@NotNull Project project) {
        instance = this;

        initDefaultBank();
    }
}
