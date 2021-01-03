package ui;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DialogBuilder;
import com.intellij.openapi.ui.DialogPanel;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.ui.TitlePanel;
import com.intellij.ui.components.JBLabel;
import com.intellij.ui.components.fields.ExtendableTextField;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;

/**
 * @author steve
 */
public class SQLSettingDialog extends DialogBuilder {
    private int clickedTimes = 0;

    private String host = "";
    private String key = "";
    private String database = "";
    private String container = "";

    public SQLSettingDialog(
        Project project) {
        super(project);
        this.setTitle("Cosmos SQL Client");
        this.resizable(false);
        TitlePanel titlePanel = new TitlePanel("Connection Settings", "connection settings");
        this.setNorthPanel(titlePanel);
        GridLayout gridLayout = new GridLayout(4, 4);

        DialogPanel dialogPanel = new DialogPanel(gridLayout);
        ExtendableTextField hostTextField = setHostTextField(dialogPanel);
        ExtendableTextField keyTextField = setKeyTextField(dialogPanel);
        ExtendableTextField databaseTextField = setDatabaseTextField(dialogPanel);
        ExtendableTextField containerTextField = setContainerTextField(dialogPanel);


        dialogPanel.setToolTipText("this is a dialog panel");
        dialogPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 200));
        this.setCenterPanel(dialogPanel);

        this.setOkOperation(() -> {
            String hostText = hostTextField.getText();
            host = hostText == null || hostText.isBlank() ? null : hostText;

            String keyText = keyTextField.getText();
            key = keyText == null || keyText.isBlank() ? null : keyText;

            String databaseText = databaseTextField.getText();
            database = databaseText == null || databaseText.isBlank() ? null : databaseText;

            String containerText = containerTextField.getText();
            container = containerText == null || containerText.isBlank() ? null : databaseText;

            Messages.showMessageDialog("host: %s\nkey: %s\ndatabase: %s\ncontainer: %s".formatted(host, key, database, container), "Input Result", Messages.getInformationIcon());

            clickedTimes++;
            // todo get connection result, register connection service

//            Messages.showMessageDialog("You've Triggered Ok Action %d times!".formatted(clickedTimes), "What's Up!", Messages.getInformationIcon());
            SQLDialog sqlDialog = new SQLDialog(project);
            sqlDialog.show();
        });
    }

    private ExtendableTextField setTextField(DialogPanel dialogPanel, String labelText) {
        ExtendableTextField textField = getExtendableTextField();

        JBLabel label = getJbLabel(textField, labelText);
        dialogPanel.add(label);
        dialogPanel.add(textField);
        return textField;
    }

    private ExtendableTextField setHostTextField(DialogPanel dialogPanel) {
        return setTextField(dialogPanel, "Host:");
    }

    private ExtendableTextField setKeyTextField(DialogPanel dialogPanel) {
        return setTextField(dialogPanel, "Key:");
    }

    private ExtendableTextField setDatabaseTextField(DialogPanel dialogPanel) {
        return setTextField(dialogPanel, "Database:");
    }

    private ExtendableTextField setContainerTextField(DialogPanel dialogPanel) {
        return setTextField(dialogPanel, "Container:");
    }


    @NotNull
    private JBLabel getJbLabel(ExtendableTextField hostTextField, String label) {
        JBLabel hostLabel = new JBLabel(label);
        hostLabel.setLabelFor(hostTextField);
        return hostLabel;
    }

    @NotNull
    private ExtendableTextField getExtendableTextField() {
        ExtendableTextField hostTextField = new ExtendableTextField();
        hostTextField.setAutoscrolls(true);
        hostTextField.setEditable(true);
        hostTextField.enableInputMethods(true);
        return hostTextField;
    }
}
