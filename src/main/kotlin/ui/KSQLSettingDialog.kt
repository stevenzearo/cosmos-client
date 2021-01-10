package ui

import client.sql.KCosmos
import client.sql.KCosmosSQLConfig
import com.intellij.openapi.project.Project
import com.intellij.openapi.ui.DialogBuilder
import com.intellij.openapi.ui.DialogPanel
import com.intellij.openapi.ui.Messages
import com.intellij.openapi.ui.TitlePanel
import com.intellij.ui.components.JBLabel
import com.intellij.ui.components.fields.ExtendableTextField
import kotlinx.coroutines.job
import kotlinx.coroutines.runBlocking
import java.awt.GridLayout
import javax.swing.BorderFactory

/**
 * @author steve
 */
class KSQLSettingDialog constructor(project: Project): DialogBuilder(project) {
    init {
        setTitle("Cosmos SQL Client")
        resizable(false)
        val titlePanel = TitlePanel("Connection Settings", "connection settings")
        setNorthPanel(titlePanel)
        val gridLayout = GridLayout(4, 4)

        val dialogPanel = DialogPanel(gridLayout)
        val hostTextField: ExtendableTextField = setHostTextField(dialogPanel)
        val keyTextField: ExtendableTextField = setKeyTextField(dialogPanel)
        val databaseTextField: ExtendableTextField = setDatabaseTextField(dialogPanel)
        val containerTextField: ExtendableTextField = setContainerTextField(dialogPanel)


        dialogPanel.toolTipText = "this is a dialog panel"
        dialogPanel.border = BorderFactory.createEmptyBorder(20, 20, 20, 200)
        setCenterPanel(dialogPanel)

        setOkOperation {
            val hostText = hostTextField.text
            val host = if (hostText == null || hostText.isBlank()) null else hostText

            val keyText = keyTextField.text
            val key = if (keyText == null || keyText.isBlank()) null else keyText

            val databaseText = databaseTextField.text
            val database = if (databaseText == null || databaseText.isBlank()) null else databaseText

            val containerText = containerTextField.text
            val container = if (containerText == null || containerText.isBlank()) null else databaseText
            if (host == null || key == null) {
                Messages.showMessageDialog(
                    "host or key must not be null",
                    "Connection Info",
                    Messages.getInformationIcon()
                )
            } else {
                var cosmos: KCosmos? = null


                try {
                    val cosmosSQLConfig = KCosmosSQLConfig(host, key, database, container)
                    cosmos = KCosmos(cosmosSQLConfig)
                    runBlocking {
                        cosmos.query("SELECT f.id FROM family f", String::class.java)
                    }.also {
                        Messages.showMessageDialog(
                            "get ${it.size} records",
                            "Query Info",
                            Messages.getInformationIcon()
                        )
                    }

                } catch (e: Exception) {
                    Messages.showMessageDialog("get cosmos connection failed", "Error Info", Messages.getErrorIcon())
                } finally {
                    cosmos?.close()
                }
            }

            // todo get connection result, register connection service

//            Messages.showMessageDialog("You've Triggered Ok Action %d times!".formatted(clickedTimes), "What's Up!", Messages.getInformationIcon());
//            SQLDialog sqlDialog = new SQLDialog(project);
//            sqlDialog.show();
        }

    }


    private fun setTextField(dialogPanel: DialogPanel, labelText: String): ExtendableTextField {
        val textField = getExtendableTextField()
        val label = getJbLabel(textField, labelText)
        dialogPanel.add(label)
        dialogPanel.add(textField)
        return textField
    }

    private fun setHostTextField(dialogPanel: DialogPanel): ExtendableTextField {
        return setTextField(dialogPanel, "Host:")
    }

    private fun setKeyTextField(dialogPanel: DialogPanel): ExtendableTextField {
        return setTextField(dialogPanel, "Key:")
    }

    private fun setDatabaseTextField(dialogPanel: DialogPanel): ExtendableTextField {
        return setTextField(dialogPanel, "Database:")
    }

    private fun setContainerTextField(dialogPanel: DialogPanel): ExtendableTextField {
        return setTextField(dialogPanel, "Container:")
    }


    private fun getJbLabel(hostTextField: ExtendableTextField, label: String): JBLabel {
        val hostLabel = JBLabel(label)
        hostLabel.labelFor = hostTextField
        return hostLabel
    }

    private fun getExtendableTextField(): ExtendableTextField {
        val hostTextField = ExtendableTextField()
        hostTextField.autoscrolls = true
        hostTextField.isEditable = true
        hostTextField.enableInputMethods(true)
        return hostTextField
    }
}