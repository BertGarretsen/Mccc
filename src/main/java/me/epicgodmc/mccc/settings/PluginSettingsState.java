package me.epicgodmc.mccc.settings;

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import com.intellij.util.xmlb.XmlSerializerUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@State(
        name = "me.epicgodmc.mccc.settings.PluginSettingsState",
        storages = @Storage("McccSettings.xml")
)
public class PluginSettingsState implements PersistentStateComponent<PluginSettingsState> {

    public CompletionCase completionCase = CompletionCase.UPPERCASE;
    public String completionSeperator = "_";

    public static PluginSettingsState getInstance() {
        return ApplicationManager.getApplication().getService(PluginSettingsState.class);
    }

    @Override
    public @Nullable PluginSettingsState getState() {
        return this;
    }

    @Override
    public void loadState(@NotNull PluginSettingsState state) {
        XmlSerializerUtil.copyBean(state, this);
    }
}
