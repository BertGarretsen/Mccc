package me.epicgodmc.mccc.settings;

import com.intellij.openapi.options.Configurable;
import com.intellij.openapi.options.ConfigurationException;
import me.epicgodmc.mccc.data.CompletionBank;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class PluginSettingsConfigurable implements Configurable
{

    private PluginSettingsComponent settingsComponent;


    @Nls(capitalization = Nls.Capitalization.Title)
    @Override
    public String getDisplayName() {
        return "Minecraft Config Completion Settings";
    }

    @Override
    public @Nullable JComponent getPreferredFocusedComponent() {
        return settingsComponent.getPreferredFocusedComponent();
    }

    @Override
    public @Nullable JComponent createComponent() {
        settingsComponent = new PluginSettingsComponent();
        return settingsComponent.getPanel();
    }


    @Override
    public boolean isModified() {
        PluginSettingsState settings = PluginSettingsState.getInstance();
        boolean modified = !settingsComponent.getSeperator().equals(settings.completionSeperator);
        modified |= settingsComponent.getCompletionCase() != settings.completionCase;
        return modified;
    }

    @Override
    public void apply() throws ConfigurationException {
        PluginSettingsState settings = PluginSettingsState.getInstance();
        settings.completionSeperator = settingsComponent.getSeperator();
        settings.completionCase = settingsComponent.getCompletionCase();
        CompletionBank.getInstance().initDefaultBank();
    }

    @Override
    public void reset() {
        PluginSettingsState settings = PluginSettingsState.getInstance();
        settingsComponent.setSeperator(settings.completionSeperator);
        settingsComponent.setCompletionCase(settings.completionCase);
    }

    @Override
    public void disposeUIResources() {
        settingsComponent = null;
    }
}
