package action;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.ui.Messages;
import org.jetbrains.annotations.NotNull;

/**
 * @author steve
 */
public class MongoClientAction extends AnAction {
    @Override
    public void actionPerformed(@NotNull AnActionEvent anActionEvent) {
        String name = Messages.showInputDialog("what is your name?", "Input Your Name Here", Messages.getQuestionIcon());
        Messages.showMessageDialog("hello, " + name, "Information", Messages.getInformationIcon());
    }
}
