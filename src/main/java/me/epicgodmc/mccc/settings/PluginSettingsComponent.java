package me.epicgodmc.mccc.settings;

import com.intellij.openapi.ui.ComboBox;
import com.intellij.ui.components.JBLabel;
import com.intellij.ui.components.JBTextField;
import com.intellij.util.ui.FormBuilder;

import javax.swing.*;

public class PluginSettingsComponent {

    private final JPanel panel;
    private final JBTextField completionSeperator = new JBTextField();
    private final ComboBox<CompletionCase> completionCasesBox = new ComboBox<>();


    public PluginSettingsComponent() {
        completionCasesBox.addItem(CompletionCase.LOWERCASE);
        completionCasesBox.addItem(CompletionCase.UPPERCASE);
        completionCasesBox.addItem(CompletionCase.BOTH);
        panel = FormBuilder.createFormBuilder()
                .addLabeledComponent(new JBLabel("Seperator: "), completionSeperator, 1, false)
                .addLabeledComponent(new JBLabel("Completion Case: "), completionCasesBox, 1, false)
                .addComponentFillVertically(new JPanel(), 0)
                .getPanel();
    }

    public JPanel getPanel() {
        return panel;
    }

    public JComponent getPreferredFocusedComponent() {
        return completionSeperator;
    }

    public String getSeperator() {
        return completionSeperator.getText();
    }

    public void setSeperator(String seperator) {
        this.completionSeperator.setText(seperator);
    }

    public CompletionCase getCompletionCase() {
        return this.completionCasesBox.getItem();
    }

    public void setCompletionCase(CompletionCase cCase) {
        this.completionCasesBox.setItem(cCase);
    }
}
