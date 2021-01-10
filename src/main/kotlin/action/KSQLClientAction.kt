package action

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.project.Project
import ui.KSQLSettingDialog

/**
 * @author steve
 */
class KSQLClientAction: AnAction() {

    override fun actionPerformed(e: AnActionEvent) {
        val project: Project? = e.project
        val kSQLSettingDialog = KSQLSettingDialog(project!!)
        kSQLSettingDialog.show()
    }

}