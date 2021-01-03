package action;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;
import ui.SQLSettingDialog;

/**
 * @author steve
 */
public class SQLClientAction extends AnAction {

    @Override
    public void actionPerformed(@NotNull AnActionEvent anActionEvent) {
        Project project = anActionEvent.getProject();

        SQLSettingDialog sqlSettingDialog = new SQLSettingDialog(project);
        sqlSettingDialog.show();
    }
}
