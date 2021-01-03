package ui;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DetailsComponent;
import com.intellij.openapi.ui.DialogBuilder;
import com.intellij.openapi.ui.DialogPanel;
import com.intellij.openapi.ui.TitlePanel;
import com.intellij.ui.components.JBLabel;
import com.intellij.ui.components.JBTextArea;

import javax.swing.*;

/**
 * @author steve
 */
public class SQLDialog extends DialogBuilder {
    public SQLDialog(Project project) {
        super(project);
        this.setTitle("Cosmos SQL Client");
        TitlePanel titlePanel = new TitlePanel("Title Panel", "title panel");
        this.setCenterPanel(getCenterPanel(project));
        this.setNorthPanel(titlePanel);
    }

    public JPanel getCenterPanel(Project project) {

        JBTextArea jbTextArea = new JBTextArea();
        jbTextArea.setBorder(BorderFactory.createEmptyBorder(20, 20, 400, 800));
        JBLabel label = new JBLabel("Input Some Things Here");

        DetailsComponent detailsComponent = new DetailsComponent();
        detailsComponent.setText("This is a dialog component");
        detailsComponent.setEmptyContentText("This is a dialog component without content");
        detailsComponent.setPaintBorder(true);

        DialogPanel dialogPanel = new DialogPanel();
        dialogPanel.add(detailsComponent.getComponent());
        dialogPanel.add(label);
        dialogPanel.add(jbTextArea);
        dialogPanel.setToolTipText("this is a dialog panel");
        dialogPanel.apply();
        return dialogPanel;
    }
}