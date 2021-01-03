package action;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DetailsComponent;
import com.intellij.openapi.ui.DialogBuilder;
import com.intellij.openapi.ui.DialogPanel;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.ui.PanelWithText;
import com.intellij.ui.JBColor;

import javax.swing.*;

/**
 * @author steve
 */
public class DemoAction extends AnAction {

    @Override
    public void actionPerformed(AnActionEvent e) {
        // TODO: insert action logic here
        Project project = e.getData(PlatformDataKeys.PROJECT);
        String name = Messages.showInputDialog(project, "what is your name?", "Input Your Name Here", Messages.getQuestionIcon());
        Messages.showMessageDialog(project, "hello, " + name, "Information", Messages.getInformationIcon());
        DetailsComponent detailsComponent = new DetailsComponent();
        detailsComponent.forProject(project);
        detailsComponent.setText("This is a dialog component");
        detailsComponent.setEmptyContentText("This is a dialog component without content");
        detailsComponent.setPaintBorder(true);
        JLabel label = new JLabel("Input Some Things Here");
        JTextArea textArea = new JTextArea();
        textArea.setRows(20);
        textArea.setSelectionColor(JBColor.RED);
        textArea.add(label);
        detailsComponent.setContent(textArea);

        PanelWithText panelWithText = new PanelWithText("hello, world!");
        DialogPanel dialogPanel = new DialogPanel();
        dialogPanel.setAlignmentX(23f);
        dialogPanel.setAlignmentY(23f);
        dialogPanel.add(detailsComponent.getComponent());
        dialogPanel.setToolTipText("this is a dialog panel");
        dialogPanel.apply();

        DialogBuilder dialogBuilder = new DialogBuilder(project);
        dialogBuilder.setTitle("Test Dialog");
        dialogBuilder.setCenterPanel(dialogPanel);
        dialogBuilder.setNorthPanel(panelWithText);
        dialogBuilder.show();

        /*
        CosmosClient cosmosClient = new CosmosClientBuilder()
            .endpoint("")
            .key("")
            .preferredRegions(List.of())
            .consistencyLevel(ConsistencyLevel.EVENTUAL)
            .contentResponseOnWriteEnabled(true)
            .buildClient();

        CosmosDatabase database = cosmosClient.getDatabase("");

        CosmosContainer container = database.getContainer("");

        CosmosQueryRequestOptions cosmosQueryRequestOptions = new CosmosQueryRequestOptions();

        container.queryItems("", cosmosQueryRequestOptions, this.getClass());
        */
        // todo cosmos mongo client using mongo-java driver.
    }
}
